import type { ReactNode } from "react";
import { X } from "lucide-react";

type Props = {
  title: string;
  onClose: () => void;
  children: ReactNode;
};

export default function Modal({ title, onClose, children }: Props) {
  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-card" onClick={e => e.stopPropagation()}>
        <div className="modal-header">
          <h4>{title}</h4>
          <button className="icon-btn" onClick={onClose}><X size={15} /></button>
        </div>
        {children}
      </div>
    </div>
  );
}
