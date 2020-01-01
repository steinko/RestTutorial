import PersonService from './PersonService'

let personService;
beforeEach(() => {
	personService = new PersonService()
})

it ('should exists', () => {
	expect(personService instanceof PersonService).toBeTruthy()
 })

 it ('should return a Person',  async () => { 
	 const person = 
    {
      "id": 2,
	  "firstName": "Stein",
	  "familyName": "Korsveien"
	}
	 
	 expect( await personService.get()).toEqual(person)

 })

 it ('should update a Person', async () => {  
	 const person = 

	 [{ "id": "2",
	  "firstName": "Sture",
	  "familyName": "Sturalason"
	 }] 

	expect( await personService.put(person)).toEqual(200)
})