```html
<input name="title" #newtitle>
```
This markup tells Angular to bind this <input> to the variable newtitle. The #newtitle syntax is called a resolve. 
The effect is that this makes the variable newtitle available to the expressions within this view.
Again, it’s important to realize that title and link are both objects of type HTMLInputElement and not the input values directly. To get the value from the input we have to call title.value. For now, we’re just going to console.log out those arguments.

JavaScript, by default, propagates the click event to all the parent components. Because the
click event is propagated to parents, our browser is trying to follow the empty link, which tells the
browser to reload. To fix that, we need to make the click event handler to return false. This will ensure the browser
won’t try to refresh the page. Let’s update our code so that each of the functions voteUp() and voteDown() return a boolean value of false (tells the browser not to propagate the event upwards):
```typescript
1 voteDown(): boolean {
2 this.votes -= 1;
3 return false;
4 }
5 // and similarly with `voteUp()`
```
#### pass input parameters to component
Angular allows us to do this by using the Input decorator on a property of a Component:
```typescript
class ArticleComponent {
   @Input() article: Article;
   // ...
```
Now if we have an Article in a variable myArticle we could pass it to our ArticleComponent in our view. Remember, 
we can pass a variable in an element by surrounding it in square brackets [variableName], like so:
```
<app-article [article]="myArticle"></app-article>
```
Notice the syntax here: we put the name of the input in brackets as in: [article] and the value of
the attribute is what we want to pass into that input. Then, and this is important, the this.article on the ArticleComponent instance will be set to myArticle. We can think about the variable myArticle as being passed as a parameter (i.e. input) to our components.
