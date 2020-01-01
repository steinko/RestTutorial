import Logger from './Logger'
const url = 'http://localhost:8080/person'
const logger = Logger.getLogger()
export default class PersonService  { 
	
     
	async get() { 
			  const response = await fetch(url);
			  const json = await response.json()
	   
		return json
	   }

	async put(person) {
     
		 try {
			
			   logger.info(person,{} )
			   logger.info('before fetch put',{})
			   logger.info(person.id,{})
		      const response = await fetch(url + '/' + person.id, 
				  {method: 'PUT',	 
				   body: person,
		         headers: { "Content-type": "application/json; charset=UTF-8" }		  
				  }
				)
		      return  response.status
		}	catch(error)
		{
		  logger.error(error,{})
	   }	
   }
}
