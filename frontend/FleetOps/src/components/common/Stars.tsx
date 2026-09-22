import { Star } from "lucide-react";

export default function Stars({ value }: { value: number }) {
  return (
    <span className="score-badge">
      <Star size={13} strokeWidth={2.4} />
      {value}
    </span>
  );
}
