
import './App.css';
import Login from './components/Login';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Route, Routes,Navigate } from 'react-router-dom';
import  EmployeeAdd from './components/EmployeeAdd';
import  AddEmployee from './components/AddEmployee';
import  VendorMessage from './components/VendorMessage';
import  Admin from './components/Admin';

function App() {
  const token = localStorage.getItem("token");
  return (
    <div className="App">
     {token && <Navbar/> }
     <Router>
     <Routes>
    <Route path='/navbar' element={token && <Admin/>}></Route>
      <Route   path="/Login" element={<Login/>}></Route>
      <Route   path="/Employee" element={token &&<EmployeeAdd/>}></Route>
      <Route   path="/AddEmployee" element={token &&<AddEmployee/>}></Route>
      <Route   path="/vendor" element={token &&<VendorMessage/>}></Route>
      <Route
                        path="/"
                        element={<Navigate to="/Login" />}
                    />
     </Routes>
     </Router>
    </div>
  );
}

export default App;
