import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/NavBar/NavBar.jsx";
import Footer from "./components/Footer.jsx";
import CoverPage from "./pages/CoverPage.jsx";
import Home from "./pages/Home.jsx";
import "./App.css";
import SideBar from "./components/SideBar.jsx";
import Dashboard from "./pages/Dashboard.jsx";

function App() {
  return (
    <>
      <NavBar />
      <div className="container-fluid">
        <SideBar />

        <Dashboard />
        
      </div>
    </>
  );
}

export default App;

{/* <NavBar />
      <div className="container-fluid">
        <SideBar />

        <Home />
        
      </div> */}