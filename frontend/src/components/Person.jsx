import React from 'react'
import { Component } from 'react'
import PersonService from './PersonService';
import Logger from './Logger'

const logger = Logger.getLogger()
let personService

export default class Person extends Component{ 

	  constructor(props)  {
			props = ''
	   super(props)
	   
		this.state  =  { id:'',
										 firstName:'',
										 familyName:'' }

		this.handleChangeId = this.handleChangeId.bind(this)
		this.handleChangeFirstName = this.handleChangeFirstName.bind(this)
		this.handleChangeFamilyName = this.handleChangeFamilyName.bind(this)
		this.handleSubmit = this.handleSubmit.bind(this)
		this.handleGet = this.handleGet.bind(this)
		
		personService = new PersonService()
	 }

    async componentDidMount(){
			if(!this.state.id === '') {
			   const person = await personService.get(this.state.id)
			   logger.info("Person id" + person.id)
			   this.setState ( {id:person.id, firstName: person.firstName, familyName: person.familyName }) 
			}
		}
		
		handleChangeId(event)  {
			this.setState( {id: event.target.value	})
		 }

		 handleChangeFirstName(event)  {
			this.setState( {firstName: event.target.value	})
		 }

		 handleChangeFamilyName(event)  {
			this.setState( {familyName: event.target.value	})
		 }

		 async handleSubmit(event)  {
      logger.info(this.state,{})
			await personService.put(this.state)
		 }
		 
		 async handleGet(event)  {
      logger.info(this.state,{})
			const person = await personService.get(this.state.id)
			logger.info( "id:" + person.id + 'firstName:' + 'person.firstName' + 'familyName:'+ person.familyName )
			this.setState ( {id:person.id, firstName: person.firstName, familyName: person.familyName }) 

		 }



	render(){ 
		const { person, isLoading } = this.state;
        if (isLoading) {
         return <p>Loading ...</p>;
        }
		
		return ( 
		<form id = "submit" onSubmit = {this.handleSubmit }>
			<label>
				Id:  
				<input 
					 id= "id"
					 data-testid= "dtid"
				   type= "text"
					 value= {this.state.id } 
					 onChange = {this.handleChangeId }
				/>	
			</label>
			<label> 
				First Name:  
				<input
				  id= "firstName"
				  type= "text"
					value= {this.state.firstName } 
					onChange = {this.handleChangeFirstName }
					/>
			</label>
			<label>
				Family Name:  
				<input
				   id= "familyName"
				   type= "text"
					 value=  {this.state.familyName } 
					 onChange = {this.handleChangeFamilyName }
				/>
			</label>

			<input 
				 type= "submit"
				 value= "Submit"
			/>

			<button 
				type="button"
				onClick = { this.handleGet }
			 >
				Get
			 </button>

			  
		</form>)
		}
}