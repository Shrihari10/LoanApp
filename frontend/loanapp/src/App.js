import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import MainComponent from "./components/MainComponent";
import { ChakraProvider } from "@chakra-ui/react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <ChakraProvider>
      <div className="AppParent"></div>
      <div className="horse"></div>
      <div className="App">
        <BrowserRouter>
          <MainComponent />
        </BrowserRouter>
        <ToastContainer />
      </div>
    </ChakraProvider>
  );
}

export default App;
