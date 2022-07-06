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
  levelNumber: number = 1;
  description: Map<number,string[]> = new Map();
  levelNames: string[] = ["Beginner", "Tutor", "Endboss"];

  constructor(private backendService: BackendService, private modalService: BsModalService) {
    this.backendService.getLevel().subscribe(levelNumber => {
      this.levelNumber = levelNumber;
      this.level = this.levelNames[levelNumber-1];
    });
    this.onLevelChange(this.levelNumber);
  }

  onLevelChange(level: number){
    switch (level) {
      case 1:
        this.level = "Beginner";
        this.levelNumber = 1;
        this.backendService.setLevel(this.levelNumber).subscribe();
        this.onDescriptionChange(level);
        break;
      case 2:
        this.level = "Tutor";
        this.levelNumber = 2;
        this.backendService.setLevel(this.levelNumber).subscribe();
        this.onDescriptionChange(level);
        break;
      case 3:
        this.level = "Endboss";
        this.levelNumber = 3;
        this.backendService.setLevel(this.levelNumber).subscribe();
        this.onDescriptionChange(level);
        break;
    }
    this.backendService.setLevel(level);
  }

  onDescriptionChange(level: number){
    switch (level) {
      case 1:
        this.description.set(1, [
          "Zu finden in der Suche, einfach ein script eingeben",
          "Zu finden - blindSqlInjection",
          "Zu finden - emailWithoutAt",
          "Zu finden - xss",
          "Zu finden - profile_picture",
          "Zu finden - htmlCommentUser",
          "Zu finden - priceOrderBug",
          "Zu finden - guessUserLogin",
          "Zu finden - guessCoupon",
          "Zu finden - deleteUser",
          "Zu finden - commentXss",
          "Zu finden - lookForEmailAddress",
          "Zu finden - hashUser"
        ],);
        break;
      case 2:
        // this.description = "";
        break;
      case 3:
        // this.description = "";
        break;
    }
  }

  getInformationArray(level: number) {
    var information: string[] = ["SQL-Injection","Blind-Sql-Injection","E-Mail without @","XSS - Cross-Site-Scripting","Profile Picture","HTML Comment User","Price Order Bug","Guess User Login","Guess Coupon","Delete User","Comment XSS - Cross-Site-Scripting","Look for E-Mail Address","Hash User"];
    var boolIdentifier: string[] = ["sqlInjection", "blindSqlInjection", "emailWithoutAt", "xss", "profile_picture", "htmlCommentUser", "priceOrderBug", "guessUserLogin", "guessCoupon", "deleteUser", "commentXss", "lookForEmailAddress", "hashUser"];
    var tmpStudent: RankingStudent = this.actualStudent;
    // @ts-ignore
    return this.description.get(level).map(function(item, index){
      let identifier = boolIdentifier[index] as keyof RankingStudent;
      return [item, information[index], tmpStudent[identifier]];
    })
  }

  ngOnInit() {
    this.backendService.getLevel().subscribe(levelNumber => this.levelNumber = levelNumber);
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
