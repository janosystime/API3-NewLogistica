import type { Theme } from "../../types/app";
import { useEffect, useRef } from "react";

type NetNode = { x: number; y: number; r: number };
type NetEdge = { key: string; a: number; b: number };
type Packet = { edge: NetEdge; t: number; speed: number };

// Fundo animado (canvas) com rede de rotas que reage ao cursor
export default function RouteNetworkBackground({ theme }: { theme: Theme }) {
  const canvasRef = useRef<HTMLCanvasElement>(null);

  useEffect(() => {
    const maybeCanvas = canvasRef.current;
    if (!maybeCanvas)
      return;
    const canvas: HTMLCanvasElement = maybeCanvas;
    const maybeCtx = canvas.getContext("2d");
    if (!maybeCtx)
      return;
    const ctx: CanvasRenderingContext2D = maybeCtx;

    const reduceMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
    const dpr = Math.min(window.devicePixelRatio || 1, 2);

    const palette = theme === "light"
      ? { bg: "#F5F6F8", roadRGB: "23,26,29", accentRGB: "255,107,53" }
      : { bg: "#14181C", roadRGB: "237,239,241", accentRGB: "255,148,102" };
    const ROAD_ALPHA = 0.09;
    const NODE_ALPHA = 0.22;

    let width = 0;
    let height = 0;
    let nodes: NetNode[] = [];
    let edges: NetEdge[] = [];
    let packets: Packet[] = [];
    let raf: number | null = null;
    const mouse = { x: -9999, y: -9999 };

    function buildNetwork() {
      const count = Math.max(14, Math.round((width * height) / 42000));
      nodes = Array.from({ length: count }, () => ({
        x: Math.random() * width,
        y: Math.random() * height,
        r: 1.6 + Math.random() * 1.6,
      }));
      edges = [];
      nodes.forEach((n, i) => {
        const nearest = nodes
          .map((m, j) => ({ j, d: i === j ? Infinity : Math.hypot(n.x - m.x, n.y - m.y) }))
          .sort((a, b) => a.d - b.d)
          .slice(0, 2);
        nearest.forEach(({ j, d }) => {
          if (d < Math.max(width, height) * 0.42) {
            const key = i < j ? `${i}-${j}` : `${j}-${i}`;
            if (!edges.some(e => e.key === key))
              edges.push({ key, a: i, b: j });
          }
        });
      });
      packets = edges.slice(0, Math.min(9, edges.length)).map(e => ({
        edge: e,
        t: Math.random(),
        speed: 0.0018 + Math.random() * 0.0022,
      }));
    }

    function resize() {
      const rect = canvas.getBoundingClientRect();
      width = rect.width;
      height = rect.height;
      canvas.width = width * dpr;
      canvas.height = height * dpr;
      ctx.setTransform(dpr, 0, 0, dpr, 0, 0);
      buildNetwork();
      if (reduceMotion)
        draw();
    }

    function draw() {
      ctx.clearRect(0, 0, width, height);
      ctx.fillStyle = palette.bg;
      ctx.fillRect(0, 0, width, height);

      edges.forEach((e) => {
        const a = nodes[e.a];
        const b = nodes[e.b];
        const mx = (a.x + b.x) / 2;
        const my = (a.y + b.y) / 2;
        const dist = Math.hypot(mx - mouse.x, my - mouse.y);
        const near = Math.max(0, 1 - dist / 220);
        ctx.strokeStyle = near > 0
          ? `rgba(${palette.accentRGB}, ${0.14 + near * 0.5})`
          : `rgba(${palette.roadRGB}, ${ROAD_ALPHA})`;
        ctx.lineWidth = 1 + near * 0.8;
        ctx.beginPath();
        ctx.moveTo(a.x, a.y);
        ctx.lineTo(b.x, b.y);
        ctx.stroke();
      });

      nodes.forEach((n) => {
        const dist = Math.hypot(n.x - mouse.x, n.y - mouse.y);
        const near = Math.max(0, 1 - dist / 180);
        ctx.beginPath();
        ctx.fillStyle = near > 0.05
          ? `rgba(${palette.accentRGB}, ${0.55 + near * 0.45})`
          : `rgba(${palette.roadRGB}, ${NODE_ALPHA})`;
        ctx.arc(n.x, n.y, n.r + near * 1.6, 0, Math.PI * 2);
        ctx.fill();
      });

      if (!reduceMotion) {
        packets.forEach((p) => {
          const a = nodes[p.edge.a];
          const b = nodes[p.edge.b];
          const x = a.x + (b.x - a.x) * p.t;
          const y = a.y + (b.y - a.y) * p.t;
          ctx.beginPath();
          ctx.fillStyle = `rgb(${palette.accentRGB})`;
          ctx.shadowColor = `rgb(${palette.accentRGB})`;
          ctx.shadowBlur = 10;
          ctx.arc(x, y, 2.4, 0, Math.PI * 2);
          ctx.fill();
          ctx.shadowBlur = 0;
          p.t += p.speed;
          if (p.t > 1)
            p.t = 0;
        });
      }
    }

    function loop() {
      draw();
      raf = requestAnimationFrame(loop);
    }

    function handleMove(e: MouseEvent) {
      const rect = canvas.getBoundingClientRect();
      mouse.x = e.clientX - rect.left;
      mouse.y = e.clientY - rect.top;
    }
    function handleLeave() {
      mouse.x = -9999;
      mouse.y = -9999;
    }

    resize();
    window.addEventListener("resize", resize);
    canvas.addEventListener("mousemove", handleMove);
    canvas.addEventListener("mouseleave", handleLeave);

    if (reduceMotion)
      draw();
    else
      loop();

    return () => {
      window.removeEventListener("resize", resize);
      canvas.removeEventListener("mousemove", handleMove);
      canvas.removeEventListener("mouseleave", handleLeave);
      if (raf)
        cancelAnimationFrame(raf);
    };
  }, [theme]);

  return <canvas ref={canvasRef} className="login-canvas" aria-hidden="true" />;
}
