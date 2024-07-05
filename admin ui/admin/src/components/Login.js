import React, { useState ,  useEffect } from 'react';
import {  useNavigate  } from 'react-router-dom'; // Import useHistory from react-router-dom
import axios from 'axios';
import './Login.css';
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Login =({ onLogin }) => {
  
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const history = useNavigate(); // Get history object from React Router

    useEffect(() => {
        // Check if user is already logged in
        const token = localStorage.getItem('token');
        if (token) {
            window.location.href = '/navbar';// Redirect to navbar if user is already logged in
        }
    }, [history]);

   
    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/auth/admin/login', {
                email,
                password
            });
            if (response.status === 200) {
                debugger
                var tokenAndusername = response.data.split(","); // Assuming the token is returned in the response data
                var token = tokenAndusername[0]; // Accessing the first value
               var username = tokenAndusername[1];
            
                localStorage.setItem("token", token);
                localStorage.setItem("username", username);
                toast.success("LOGIN SUCCESFULLY")
              window.location.href = '/navbar'; // Redirect to navbar page
            } else {
                toast.success("Login failed")
                
            }
        } catch (error) {
            console.error('Login error:', error);
        }
    };
  
return(
  
<div className="login_container">
    <h2 className="login">LOGIN</h2>
    <div className="underline"></div>
<div className="input">
    <input type="email" placeholder="Email" title="Email" onChange={(e) => setEmail(e.target.value)} />
</div>
<div  className="input">
    <input type="password" placeholder="Password" title="Password" onChange={(e) => setPassword(e.target.value)}/>
</div>
<div  className="forgot">
<a href="/navbar">Forgot Password</a>
</div>
<div className="btn">
    <button onClick={handleLogin} >LOGIN</button>
</div>
<ToastContainer/>
</div>
   

);

}

export default Login;