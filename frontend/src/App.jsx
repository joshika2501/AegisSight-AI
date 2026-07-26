import "./App.css";
import Dashboard from "./pages/Dashboard/Dashboard";
import Login from "./pages/Login/Login";

function App() {
  if (window.location.pathname === "/dashboard") {
    return <Dashboard />;
  }

  return <Login />;
}

export default App;
