import PersonService from './../PersonService'
import fetchMock from 'fetch-mock'

let personService;

const person1 = {
                  id: "1",
                  firstName : 'Stein',
                  familyName: 'Korsveien'
						}
const person2 = {
                  id: "2",
                  firstName : 'Oddmund',
                  familyName: 'Korsveien'
						}

const person3 = {
                  id: "1",
                  firstName : 'Sturlar',
                  familyName: 'Sturlarson'
						}

beforeAll(() => {
						
  fetchMock.get('http://localhost:8080/person?1',JSON.stringify(person1), { status: 200 })
  fetchMock.get('http://localhost:8080/person?2',JSON.stringify(person2), { status: 200 })
  fetchMock.put('http://localhost:8080/person/update',JSON.stringify(person3), { status: 200 })
})
beforeEach(() => {
	personService = new PersonService()
})

it ('should exists', () => {
	expect(personService instanceof PersonService).toBeTruthy()
 })

 it ('should return a Person with id 1',  async () => {  
	 expect( await personService.get('1')).toEqual(person1)
 })

 it ('should return a Person with id 2',  async () => {  
	 expect( await personService.get('2')).toEqual(person2)
 })

 it ('should update a Person', async () => {  
	expect( await personService.put(person3)).toEqual(person3)
})