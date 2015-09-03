var gulp = require("gulp");
var uglify = require("gulp-uglify");
var jshint = require("gulp-jshint");
var concat = require("gulp-concat");

gulp.task("concat",function(){
	return gulp.src(['./js/circle.js','./js/hello.js'])
	.pipe(concat('all.js'))
	.pipe(gulp.dest('./'));
})