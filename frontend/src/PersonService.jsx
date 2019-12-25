export default class PersonService  { 

   
	async get() { 
			
	 
			  const response = await fetch('http://localhost:8080/person');
			  const json = await response.json()
	   
		return json}
}