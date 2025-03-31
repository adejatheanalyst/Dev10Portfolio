import {Link} from "react-router-dom";

export default function ContactForm(){
    return <div className='container'>
    <form>
        <h1>Contact Form</h1>
        <div className='mb-3'>
            <label className='form-label' htmlFor='section'>Enter your name</label>
            <input className='form-control' type='text' />
        </div>
        <div className='mb-3'>
            <label className='form-label'>Enter your email:</label>
            <input className='form-control' type='email' />
        </div>
        <div className='mb-3'>
            <label className='form-label'>Enter your phone number:</label>
            <input className='form-control' type='number' />
        </div>
        <div className='mb-3'>
            <label className='form-label'>Enter your Message</label>
            <input className='form-control' type='text' />
        </div>
        <div className='mb-3'>
            <button type='submit' className='btn btn-primary me-2'>Submit</button>
            <Link type= 'btn' className='btn btn-secondary' to={'/solar-panels'}>Cancel</Link></div>
    </form>
    </div>
}