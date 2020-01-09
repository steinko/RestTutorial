import Logger from './Logger'
const url = 'http://localhost:8080/person'
const logger = Logger.getLogger()
export default class PersonService  { 
	
     
	async get(id) { 
			  const response = await fetch(url + '?id=' + id);
			  logger.info( "response get person" +  response.body)
			  const json = await response.json()
	   
		return json
	   }

	async put(person) {
     
		 try {
			
			   logger.info(person,{} )
			   logger.info('before fetch put',{})
			   logger.info(person.id,{})
		      const response = await fetch(url + '/update', 
				  {method: 'PUT',	 
				   body: person,
		         headers: { "Content-type": "application/json; charset=UTF-8" }		  
				  }
				)
		      return  await response.json()
		}	catch(error)
		{
		  logger.error(error,{})
	   }	
   }
}
