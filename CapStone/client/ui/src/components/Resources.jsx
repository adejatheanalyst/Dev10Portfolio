import { useEffect, useState } from "react";
import { Flex, Card, Stack, Button } from "@chakra-ui/react"

export default function Resources() {
    const [resources, setResources] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch(`${import.meta.env.VITE_APP_API_URL}/resource`)
        .then(res => res.json()
        .then(fetchResources => {
            setLoading(false)
            setResources(fetchResources)
        }))
    }, [])
    if(resources.length === 0){
        if(loading){
            return (<p>Loading...</p>
    
            )
        }else{
            return(<p>Loading...</p>
            )
        }
    }
    return(
        <div class="my-3 p-3  rounded shadow-sm">
        <h6 class="border-bottom pb-2 mb-0 light">Resources</h6>
        <ul class="list-group">
        {resources.map(resource => (
            <li  class="resources list-group-item-action" key={resource.resourceId} >
        <div >
          <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
            <div class="d-flex justify-content-between">
              <strong class="--bs-light-text-emphasis">{resource.title}</strong>
              <a href={resource.resourceUrl} target="_blank" rel="noopener noreferrer"className="link">View Resource</a>
            </div>
          </div>
        </div>
        </li>
        ))}

        </ul>
      </div>
    //     <div>
    //         {/* <form class="d-flex" role="search">
    //     <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
    //     <button class="btn btn-outline-success" type="submit">Search</button>
    //   </form> */}
    //         <h1>Resources</h1>
    //         <ul class="list-group">
    //             {resources.map(resource => (
    //                 <li class= "list-group-item" key={resource.resourceId}>{resource.title}<br/>
    //                 {resource.resourceUrl}</li>
    //             ))}
    //         </ul>
    //         </div>
    )




}
