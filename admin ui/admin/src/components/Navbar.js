import React from "react";
import "./Navbar.css"

import "react-toastify/dist/ReactToastify.css";
const Navbar = () =>{


    const logout = () =>{
       
        localStorage.clear();
       // Check if user is already logged in
       const token = localStorage.getItem('token');
       if (!token) {
           window.location.href = '/Login';// Redirect to navbar if user is already logged in
       }   
    }

    return (
     
        <div className="container_nav">
              <h2>USER Management System</h2>
  <div className="navbar">
  <ul>
           <a href="/vendor"><button>Vendor Message</button></a>
        </ul>
        <ul>
           <a href="/Employee"><button>USER DETAILS</button></a>
        </ul>
        <ul>
            <button className="btn_logout" onClick={logout}>LOGOUT</button>
        </ul>
    </div>

        </div>
      
       
    );

}

export default Navbar;