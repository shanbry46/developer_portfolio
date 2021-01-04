import React from 'react';
import './Home.css'
import {useHistory} from 'react-router-dom'

const Home = () => 
{
    const history = useHistory();
    const handleClick = (searchVal:string) => {
        // the query parameter can be changed but I wanted it
        // to be provided so it can be referenced by the API
        history.push(`/search?val=${searchVal}`);
    }

    return (
      <div className='wrapper'>
        <div className='form-wrapper' id='home-fw'>
            <h2>Search Fair Price</h2>

            <div className='search'>
                <input type="text" placeholder="Search.." name="search" id="searchVal" />
            </div>
            <div className='submit'>
                <button
                    className='submit-btn'
                    type='submit' onClick={() => handleClick((document.getElementById("searchVal") as HTMLInputElement).value)}>
                    Search
                </button>
            </div>
        </div>
      </div>
    )
}

export default Home