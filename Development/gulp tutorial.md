gulp is a streaming build system. 

* allow it to pipe and pass around the data being manipulated or used by its plug-ins
* the plug-ins are only to do one job each. we can pass single file through multiple plug-ins
4 major functions in gulp:
* gulp.task
* gulp.src
* gulp.dest
* gulp.watch

gulp.task is used to define your job, parameters are name, [deps], functions
``` javascript
gulp.task('mytask', function() {
  //do stuff});

gulp.task('dependenttask', ['mytask'], function() {
  //do stuff after 'mytask' is done.});
``` 
gulp.src points to the files we want to use. It uses .pipe for chaining its output into other plug-ins

gulp.dest points to the output folder we want to write files to. example is followed:
```javascript
gulp.task('copyHtml', function() {
  // copy any html files in source/ to public/
  gulp.src('source/*.html').pipe(gulp.dest('public'));
});
``` 
gulp.watch is ue to return an EventEmitter to inform change events.
```
gulp.watch('source/javascript/**/*.js', ['jshint']);
```
above code means any of the files changed in source/javascript subfolders with js extension, run the jsjint task to those files.




Reference: https://scotch.io/tutorials/automate-your-tasks-easily-with-gulp-js
