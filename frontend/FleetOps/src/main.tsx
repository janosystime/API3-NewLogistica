import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App";
import UserProvider from "./providers/UserProvider";

createRoot(document.getElementById("root")!).render(
  <UserProvider>
    <StrictMode>
      <App />
    </StrictMode>
  </UserProvider>,
);
