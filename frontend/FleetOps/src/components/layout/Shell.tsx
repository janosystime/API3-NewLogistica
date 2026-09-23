import type { ReactNode } from "react";
import type { Theme } from "../../types/app";

type Props = {
  children: ReactNode;
  theme: Theme;
};

// Casca da aplicação: aplica o tema
export default function Shell({ children, theme }: Props) {
  return (
    <div className={`shell theme-${theme}`}>
      <div className="canvas">{children}</div>
    </div>
  );
}
