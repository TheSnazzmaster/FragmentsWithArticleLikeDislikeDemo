# FragmentsWithArticleLikeDislikeDemo

An app that displays an article to the user and allows them to give the feedback of liking or disliking it.
When the user selects "Give Feedback," a fragment is added (and therefore displayed. 
The fragment shows the article title and and two radio buttons to like or dislike the article.
If the user selects that they didn't like the article, the Activity shows a new article and removes the feedback Fragment.
(Right now, it also shows a Toast with text "Closed" just to demo Activity to Fragment communication)
When the fragment is visible and the user selects "Close Feedback," the fragment is removed.
*App was created as Fragments demo for my students.*

Concepts:
- Dynamically adding Fragments
- FragmentManager
- FragmentTransactions 
- Adding/replacing/removing Fragments
- Making a Fragment instance with a bundle attached via newInstance method in Fragment
- Retrieving arguments within the Fragment
- Fragment to Activity communication via an interface
- Activity to Fragment communication via public methods

![Screenshot of app](https://github.com/alj968/FragmentsWithArticleLikeDislikeDemo/blob/master/1.png "Initial View")
![Screenshot of app](https://github.com/alj968/FragmentsWithArticleLikeDislikeDemo/blob/master/2.png "After selecting disliked article")
![Screenshot of app](https://github.com/alj968/FragmentsWithArticleLikeDislikeDemo/blob/master/3.png "After specifying dislike for the article")
