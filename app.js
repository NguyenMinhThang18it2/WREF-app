require("dotenv").config();

const express = require("express");
const app = express();
const bodyParser = require("body-parser");
var path = require("path");

require("./service/crawlData");

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, "public")));
// passport
const passport = require("passport");
const passportConfig = require("./middlewares/passport");
// api
const apiPosts = require("./router/api/posts.api");
const apiUser = require("./router/api/user.api");
const apiLogin = require("./router/api/login.api");
const apiComment = require("./router/api/comment.api");
const apiLocation = require("./router/api/location.api");
const apiStories = require("./router/api/stories.api");
const apiWeather = require("./router/api/weather.api");
const apiInforAgri = require("./router/api/inforAgri.api");

// router api passport.authenticate('jwt', { session: false })
app.use("/api", apiLogin);
app.use("/api", apiInforAgri);
app.use("/api", passport.authenticate("jwt", { session: false }), apiPosts);
app.use("/api", passport.authenticate("jwt", { session: false }), apiUser);
app.use("/api", passport.authenticate("jwt", { session: false }), apiComment);
app.use("/api", passport.authenticate("jwt", { session: false }), apiLocation);
app.use("/api", passport.authenticate("jwt", { session: false }), apiStories);
app.use("/api", passport.authenticate("jwt", { session: false }), apiWeather);

module.exports = app;