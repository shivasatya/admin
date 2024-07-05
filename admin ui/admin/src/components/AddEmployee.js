import axios from "axios";
import React, { useState } from "react";
import './Addemp.css'
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AddEmployee =() =>{

    const [userName , setUserName] =useState("");
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword,setConfirmPassword] = useState("");
    const [ctc,setctc] = useState("");
    const [designation,setdesignation] = useState("");
    const [upi,setupi] = useState("");
    const [roles,setroles] = useState("");
    const token = localStorage.getItem("token");

    const userData = {
        userName,
        email,
        password,
        ctc,
        designation,
        upi,
        confirmPassword,
        roles
      };
      
      
const addEmp = async (e) => {
    e.preventDefault();
    try {
        if (password !== confirmPassword) {
           toast.error("password and confirm Password is not Match");
            return;
          }

          const response = await axios.post(
            'http://localhost:8080/admin/add/User',
            userData,
            {
              headers: {
                'Authorization': `Bearer ${token}`
              }
            }
          );
        
       

        if(response){
            toast.success(response.data);
        }
        else{
            toast.error("Emylopee Not Added");
        }
    } catch (error) {
        
    }

}

    return(
<div className="Addemployee_container">
    <form onSubmit={addEmp} className="form">
    <div className="input">
        <input type="text" placeholder="UserName" title="userName" onChange={(e) => setUserName(e.target.value)} required/>
    </div>
    <div className="input">
        <input type="email" placeholder="Email" title="email" required onChange={(e) => setEmail(e.target.value) }/>
    </div>
    <div className="input">
        <input type="password" placeholder="Password" title="password"required onChange={(e) => setPassword(e.target.value)}/>
    </div>
    <div className="input">
        <input type="password" placeholder="Confirm password" title="Confirm password"  required onChange={(e) => setConfirmPassword(e.target.value)}/>
    </div>
    <div className="input">
            <select  
              value={roles}
                onChange={(e) => setroles(e.target.value)} 
                required 
                title="Roles">
                <option value="" disabled>Select Role</option>
                <option value="VENDOR">Vendor</option>
                <option value="EMPLOYEE">Employee</option>
            </select>
        </div>

        {roles === 'EMPLOYEE' && (
                <>
                    <div className="input">
                        <input 
                            type="number" 
                            placeholder="CTC" 
                            title="CTC"  
                            onChange={(e) => setctc(e.target.value)}
                        />
                    </div>
                    <div className="input">
                        <input 
                            type="text" 
                            placeholder="Designation" 
                            title="Designation"  
                            onChange={(e) => setdesignation(e.target.value)}
                        />
                    </div>
                </>
            )}
    
    
    <div className="input">
    {roles === 'VENDOR' && (
        <input type="text" placeholder="upi" title="upi" required onChange={(e) => setupi(e.target.value)}/>
    )}
    </div>
  

    <div className="input">
       
    </div>
    <div className="btn">
        <button type="submit">ADD EMPLOYEE</button>
    </div>
   
    </form>
    
  <ToastContainer/>
</div>
    );

}

export default AddEmployee;