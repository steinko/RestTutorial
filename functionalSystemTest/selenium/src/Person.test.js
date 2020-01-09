import getDriver from "./setupTests"
import {getHostName} from "./setupTests"
import {By, Key} from 'selenium-webdriver'

let driver
let url

beforeAll(() => {
   driver = getDriver()
	url = getHostName()
	driver.get(url)
 })

 describe('get person', () => {

   it( 'should return a blank screen'	, async () => {
	   await verifyDetailInformation('','','')
   }) 

   async function verifyDetailInformation (aId,aFirstName,aFamilyName)  { 
	  
	   const id = await driver.findElement(By.id('id')).getAttribute('value')
	   expect(id).toBe(aId)
	   const firstName = await driver.findElement(By.id('firstName')).getAttribute('value')
	   expect(firstName).toBe(aFirstName)
	   const familyName = await driver.findElement(By.id('familyName')).getAttribute('value')
	   expect(familyName).toBe(aFamilyName)

   }



   xit ('should reurn details for person with id 1 with first name  Oddmund and family name Korsveien',async () => { 
     
	 const id = await driver.findElement(By.id('id')).sendKeys('1',Key.ENTER)
	 const button = await driver.findElement(By.tagName('button')).click()

	 const firstName = await driver.findElement(By.id('firstName'))
	 expect(await firstName.getAttribute('value')).toBe('Oddmund')

     const familyName = await driver.findElement(By.id('familyName'))
	 expect(await familyName.getAttribute('value')).toBe('Korsveien')

	 id.clear().sendKeys('2')
	 button.click()
	 expect(await firstName.getAttribute('value')).toBe('Stein')
	 expect(await familyName.getAttribute('value')).toBe('Korsveien')

   })

   

 })

