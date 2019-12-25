import React from 'react'
import { Component } from 'react'
import PersonService from './PersonService';

export default class Person extends Component{ 
	  constructor(props)  {
			props = ''
	   super(props)
	   
		this.state  =  { id:null,
										 firstName:'',
										 familyName:'' }
	 }

    async componentDidMount() {
		
	  
	  const personService = new PersonService()
	  const person = await personService.get()
	  this.setState ( {id:person.id,firstName: person.firstName, familyName: person.familyName }) 


	  }

	render(){ 
		const { person, isLoading } = this.state;
        if (isLoading) {
         return <p>Loading ...</p>;
        }
		
		return ( <form>
			<label>
				Id:
				<input 
				   id= "id"
				   type= "text"
				   value= {this.state.id } 
				/>	
			</label>
			<label> 
				First Name:
				<input
				  id= "firstName"
				  type= "text"
				  value= {this.state.firstName } 
					/>
			</label>
			<label>
				Family Name:
				<input
				   id= "familyName"
				   type= "text"
				   value=  {this.state.familyName } 
				/>
			</label>
		</form>)
		}
}