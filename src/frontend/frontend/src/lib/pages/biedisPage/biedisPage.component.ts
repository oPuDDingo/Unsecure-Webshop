import {Component, OnInit, TemplateRef} from "@angular/core";
import {BackendService} from "../../data-access/service/backend.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {RankingStudent} from "../../data-access/models/rankingStudent";

@Component({
  selector: 'biedis-page',
  templateUrl: './biedisPage.component.html',
  styleUrls: ['./biedisPage.component.scss']
})

export class BiedisPageComponent implements OnInit{


  // @ts-ignore
  rankingStudents: RankingStudent[];
  // @ts-ignore
  actualStudent: RankingStudent;
  modalRef?: BsModalRef;
  level: string = "Beginner";
  description: Map<number,string[]> = new Map();

  constructor(private backendService: BackendService, private modalService: BsModalService) {
    let habitat: string[] = ['', ''];
    this.description.set(1, habitat);
    this.rankingStudents = [{
      ipAddress: "145634752457", blindSqlInjection: true, sqlInjection: true, commentXss: true, xss: false, deleteUser: true, emailWithoutAt: false, hashUser: true, guessCoupon: true, guessUserLogin: true, htmlCommentUser: true, lookForEmailAddress: true, priceOrderBug: false, profile_picture: false, points: 420
    }]
  }

  onLevelChange(level: number){
    switch (level) {
      case 1:
        this.level = "Beginner";
        this.onDescriptionChange(level);
        break;
      case 2:
        this.level = "Tutor";
        this.onDescriptionChange(level);
        break;
      case 3:
        this.level = "Endboss";
        this.onDescriptionChange(level);
        break;
    }
    this.backendService.setLevel(level);
  }

  setDescription(){
    if(this.level == "Beginner"){
      // this.description = "";
    }
  }

  onDescriptionChange(level: number){
    switch (level) {
      case 1:
        // this.description.set(1, ['',])
        break;
      case 2:
        // this.description = "";
        break;
      case 3:
        // this.description = "";
        break;
    }
  }

  ngOnInit() {
    this.backendService.setLevel(1);
    this.backendService.loadRankingStudents().subscribe(rankingStudents => this.rankingStudents = rankingStudents);

    this.reloadRanking();
  }

  reloadRanking() {
      setInterval(function(){
      }, 10000);
      this.backendService.loadRankingStudents().subscribe(rankingStudents => this.rankingStudents = rankingStudents);
  }

  getSecurityBreaches(rankingStudent: RankingStudent): number{
    let securityBreachCounter: number = 0;
    if(rankingStudent.blindSqlInjection)
      securityBreachCounter++;
    if(rankingStudent.xss)
      securityBreachCounter++;
    if(rankingStudent.profile_picture)
      securityBreachCounter++;
    if(rankingStudent.priceOrderBug)
      securityBreachCounter++;
    if(rankingStudent.lookForEmailAddress)
      securityBreachCounter++;
    if(rankingStudent.htmlCommentUser)
      securityBreachCounter++;
    if(rankingStudent.guessUserLogin)
      securityBreachCounter++;
    if(rankingStudent.guessCoupon)
      securityBreachCounter++;
    if(rankingStudent.hashUser)
      securityBreachCounter++;
    if(rankingStudent.emailWithoutAt)
      securityBreachCounter++;
    if(rankingStudent.commentXss)
      securityBreachCounter++;
    if(rankingStudent.sqlInjection)
      securityBreachCounter++;
    if(rankingStudent.deleteUser)
      securityBreachCounter++;
    return securityBreachCounter;
  }

  openModal(securityBreaches: TemplateRef<any>, rankingStudent: RankingStudent) {
    if(rankingStudent){
      this.actualStudent = rankingStudent;
    } else {
      this.actualStudent.ipAddress = "-1";
    }
      this.modalRef = this.modalService.show(securityBreaches, {animated: true});
  }

  onShopReset() {
    this.backendService.shopReset();
  }

  onRankingReset() {
    this.backendService.rankingReset();
  }

}
