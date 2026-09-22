import type { DriverStatus } from "../../types/driver";
import { STATUS } from "../../constants/status";

type Props = {
  status: DriverStatus;
  compact?: boolean;
};

export default function StatusPill({ status, compact }: Props) {
  const s = STATUS[status];
  return (
    <span className="pill" style={{ color: s.color, background: s.bg }}>
      <span className="dot" style={{ background: s.color }} />
      {compact ? s.label.split(" · ")[0] : s.label}
    </span>
  );
}
