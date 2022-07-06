import {Component, OnInit, TemplateRef} from "@angular/core";
import {BackendService} from "../../data-access/service/backend.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {RankingStudent} from "../../data-access/models/rankingStudent";
import {Router} from "@angular/router";

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

  constructor(private backendService: BackendService, private modalService: BsModalService, private router: Router) {
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
        this.description.set(2, [
          "Level 2 Zu finden in der Suche, einfach ein script eingeben",
          "Level 2 Zu finden - blindSqlInjection",
          "Level 2 Zu finden - emailWithoutAt",
          "Level 2 Zu finden - xss",
          "Level 2 Zu finden - profile_picture",
          "Level 2 Zu finden - htmlCommentUser",
          "Level 2 Zu finden - priceOrderBug",
          "Level 2 Zu finden - guessUserLogin",
          "Level 2 Zu finden - guessCoupon",
          "Level 2 Zu finden - deleteUser",
          "Level 2 Zu finden - commentXss",
          "Level 2 Zu finden - lookForEmailAddress",
          "Level 2 Zu finden - hashUser"
        ],);
        break;
      case 3:
        this.description.set(3, [
          "Level 3 Zu finden in der Suche, einfach ein script eingeben",
          "Level 3 Zu finden - blindSqlInjection",
          "Level 3 Zu finden - emailWithoutAt",
          "Level 3 Zu finden - xss",
          "Level 3 Zu finden - profile_picture",
          "Level 3 Zu finden - htmlCommentUser",
          "Level 3 Zu finden - priceOrderBug",
          "Level 3 Zu finden - guessUserLogin",
          "Level 3 Zu finden - guessCoupon",
          "Level 3 Zu finden - deleteUser",
          "Level 3 Zu finden - commentXss",
          "Level 3 Zu finden - lookForEmailAddress",
          "Level 3 Zu finden - hashUser"
        ],);
        break;
    }
  }

  getInfoSites(vulnerability: string) {
    if(vulnerability == "SQL-Injection"){
      this.router.navigateByUrl('/sql_injection');
    }
    if(vulnerability == "Blind-Sql-Injection"){
      this.router.navigateByUrl('/blind_sql_injection');
    }
    if(vulnerability == "E-Mail without @"){
      this.router.navigateByUrl('/email_without_at');
    }
    if(vulnerability == "XSS - Cross-Site-Scripting"){
      this.router.navigateByUrl('/xss');
    }
    if(vulnerability == "Profile Picture"){
      this.router.navigateByUrl('/profile_picture');
    }
    if(vulnerability == "HTML Comment User"){
      this.router.navigateByUrl('/html_comment_user');
    }
    if(vulnerability == "Price Order Bug") {
      this.router.navigateByUrl('/price_order_bug');
    }
    if(vulnerability == "Guess User Login") {
      this.router.navigateByUrl('/guess_user_login');
    }
    if(vulnerability == "Guess Coupon") {
      this.router.navigateByUrl('/guess_coupon');
    }
    if(vulnerability == "Delete User") {
      this.router.navigateByUrl('/delete_user');
    }
    if(vulnerability == "Comment XSS - Cross-Site-Scripting") {
      this.router.navigateByUrl('/sql_injection');
    }
    if(vulnerability == "Look for E-Mail Address") {
      this.router.navigateByUrl('/look_for_email_address');
    }
    if(vulnerability == "Hash User") {
      this.router.navigateByUrl('/hash_user');
    }
  }

  getInformationArray(level: number) {
    var information: string[] = [
      "SQL-Injection",
      "Blind-Sql-Injection",
      "E-Mail without @",
      "XSS - Cross-Site-Scripting",
      "Profile Picture",
      "HTML Comment User",
      "Price Order Bug",
      "Guess User Login",
      "Guess Coupon",
      "Delete User",
      "Comment XSS - Cross-Site-Scripting",
      "Look for E-Mail Address",
      "Hash User"];

    var boolIdentifier: string[] = [
      "sqlInjection",
      "blindSqlInjection",
      "emailWithoutAt",
      "xss",
      "profile_picture",
      "htmlCommentUser",
      "priceOrderBug",
      "guessUserLogin",
      "guessCoupon",
      "deleteUser",
      "commentXss",
      "lookForEmailAddress",
      "hashUser"];

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
