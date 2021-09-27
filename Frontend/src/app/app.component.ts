import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { PersonService } from './services/person/person.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  
  personForm: FormGroup;
  people:any;
  errorPerson:any = null;
  constructor(
    public fb:FormBuilder,
    public personService: PersonService,
  ){

  }
  dtTrigger: Subject<any> = new Subject();
  dtOptions: DataTables.Settings = {};
  ngOnInit(): void {
    this.getPeople();
    this.personForm = this.fb.group({    
	  identification : ['', Validators.required],
	  name : ['', Validators.required],
	  last_name : ['', Validators.required],
	  age : ['', Validators.required],
	  height : ['', Validators.required]
    })
  }



  save(): void{
    this.personService.savePerson(this.personForm.value).subscribe(resp=>{     
      if (resp.status != 208){
        console.log(resp.body)
        this.personForm.reset();
        this.people.push(resp.body);      
      }else if(resp.status == 208)
      this.errorPerson = 'La identificación ya se encuentra registrada.';
    },
    error=> {
      console.error(error)    
    }
    )
  }

  getPeople():void{
    
    this.personService.getAllPerson().subscribe(resp=>{
      this.people = resp;
    })
  }
}
