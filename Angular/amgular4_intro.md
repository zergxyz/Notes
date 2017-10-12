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
