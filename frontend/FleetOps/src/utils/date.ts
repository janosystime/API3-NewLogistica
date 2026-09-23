import { TODAY } from "../constants/app";

export function formatDate(iso: string): string {
  const [, m, d] = iso.split("-");
  return `${d}/${m}`;
}

export function ageDays(iso: string): number {
  const then = new Date(iso).getTime();
  const now = new Date(TODAY).getTime();
  return Math.round((now - then) / 86400000);
}
