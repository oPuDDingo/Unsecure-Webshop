import {Component, OnInit, TemplateRef} from "@angular/core";
import {BackendService} from "../../data-access/service/backend.service";
import {RankingStudent} from "../../data-access/models";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {UserStore} from "../../data-access/service/store/user.store";

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
  level: string = "Levels";

  constructor(private backendService: BackendService, private modalService: BsModalService) {
    this.rankingStudents = [{
      ipAddress: "145634752457", blindSqlInjection: true, sqlInjection: true, commentXss: true, xss: false, deleteUser: true, emailWithoutAt: false, hashUser: true, guessCoupon: true, guessUserLogin: true, htmlCommentUser: true, lookForEmailAddress: true, priceOrderBug: false, profile_picture: false, points: 420
    }]
  }

  onLevelChange(level: number){
    switch (level) {
      case 1:
        this.level = "Beginner";
        break;
      case 2:
        this.level = "Tutor";
        break;
      case 3:
        this.level = "Endboss";
        break;
    }

    this.backendService.setLevel(level);
  }

  ngOnInit() {
    this.backendService.loadRankingStudents().subscribe(rankingStudents => this.rankingStudents = rankingStudents);
    this.reloadRanking();
  }

  reloadRanking() {
      setInterval(function(){
        console.log("Ranking läuft");
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
