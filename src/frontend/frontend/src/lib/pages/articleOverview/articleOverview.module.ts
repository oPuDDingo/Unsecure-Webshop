import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ArticleOverviewComponent} from "./articleOverview.component";
import {SpecificationsComponent} from "./specifications/specifications.component";
import {OverviewComponent} from "./overview/overview.component";
import {CommentComponent} from "./comment/comment.component";
import {ImageSectionComponent} from "./imageSection/imageSection.component";


@NgModule({
  declarations: [ArticleOverviewComponent, SpecificationsComponent, OverviewComponent, ImageSectionComponent, CommentComponent],
  imports: [
    BrowserModule,
  ],
  providers: [],
  bootstrap: [],
  exports: [ArticleOverviewComponent]
})
export class ArticleOverviewModule {
}
