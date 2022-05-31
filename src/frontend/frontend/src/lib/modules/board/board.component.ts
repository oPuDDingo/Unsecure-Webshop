import {Component, Input, OnInit} from "@angular/core";
import {Article} from "../../data-access/models/article";
import {ArticleStore} from "../../data-access/service/article.store";

@Component({
  selector: 'board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})

export class BoardComponent implements OnInit {

  @Input()
  articles: Article[];

  constructor(private articleStore: ArticleStore) {
    this.articles = [{
      articleNumber: 1,
      modelName: "Iphone 7",
      amount: 567.98,
      brand: "Apple",
      stars: 0,
      operatingSystem: "",
      releaseDate: "",
      screen: "",
      resolution: "",
      pictures: [],
    },
      {
        articleNumber: 2,
        modelName: "Iphone 8",
        amount: 667.98,
        brand: "Apple",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 3,
        modelName: "Iphone 13",
        amount: 1749.98,
        brand: "Apple",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 4,
        modelName: "Galaxy A5",
        amount: 800.98,
        brand: "Samsung",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 5,
        modelName: "Galaxy 13",
        amount: 800.98,
        brand: "Samsung",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 6,
        modelName: "Xperia ",
        amount: 800.98,
        brand: "Samsung",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 7,
        modelName: "Xperia ",
        amount: 800.98,
        brand: "Samsung",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 8,
        modelName: "Xperia ",
        amount: 800.98,
        brand: "Samsung",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      },
      {
        articleNumber: 9,
        modelName: "Xperia ",
        amount: 800.98,
        brand: "Samsung",
        stars: 0,
        operatingSystem: "",
        releaseDate: "",
        screen: "",
        resolution: "",
        pictures: [],
      }]
  }

  ngOnInit() {
    this.articleStore.loadArticles().subscribe(articles => this.articles = articles);
  }

}
