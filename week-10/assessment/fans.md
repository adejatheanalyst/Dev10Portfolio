# Fans!

Use React to create a fansite for your team's current obsession. If you don't share an obsession, a slightly-less-than-obsession compromise is just fine.

**Examples**

- https://thejeopardyfan.com/
- https://violetbeauregardefansite.weebly.com/
- https://prince.org/
- http://ponylandpress.com/

Fansites are diverse. There's no common set of features. That means your team has a lot of latitude in the design and implementation of your fansite. There is no official specification. Work together to decide what should be included.

The examples above are likely too large to implement with time constraints, so be aware of your limitations. If your ambitions are large, prioritize your features so you can deliver features in order.

## Goals

- Coordinate tasks across a team.
- Practice using Git with a team.
- More and different React practice.
- Effective navigation in a React application.
- Focus on visual content: images, CSS, video.
- Use varied data strategies.

### Data Strategies

Use at least three of the four data strategies below.

1. Static: embed content/data directly into a component. Static content is sometimes used in "contact us" views, "about us" views, legal privacy notices, etc.

2. Public APIs: Github has a large list of [public APIs](https://github.com/public-apis/public-apis), but don't feel limited to the list. There are many more. For any API you choose, you'll need to learn how to use it. Some APIs require an access key, some don't. Some support CORS, some don't. Some require registration, some don't. Your team will need to research, prototype, and determine if it's something you can work with.

3. Lightweight, auto-generated APIs: one example is [json-server](https://github.com/typicode/json-server).

4. A database-backed Java, Spring Boot/MVC API

### Navigation

Fansites aren't known for their elegant navigation and user experience. They're labors of love. We want to change that perception.

Your fansite will have several views. Consider how best to navigate to those views, including sub-navigation within a particular view. Make it nice.

### Images, CSS, Effects, and Video

Fansites are more fun when they're visually stimulating. Make your UI fun with images, CSS transitions and effects, and video.

Images can be stored locally in the `public` directory, embedded directly as a resource using `create-react-app`, or referenced as an absolute URI.

Push your UI further with CSS framework components that include transitions and effects. Sample components: image carousels, modal dialogs, and ["toast"](https://css-tricks.com/toast/).

Also consider using a framework other than Bootstrap or try tackling more of your own CSS if it's of interest.

- https://getbootstrap.com/
- https://tailwindcss.com/
- https://bulma.io/

YouTube, Vimeo, and others can be embedded into React components. Smaller videos (don't commit and push large files to Github) can be embedded directly with the `<video>` tag.

## Approach

1. One person creates a new Github repository.

2. Add an appropriate `.gitignore` file. Your `.gitignore` should account for JavaScript and Java artifacts. (The `.gitignore` in the `dev10-classwork` repo should work.)

3. Add your teammates and instructors as collaborators.

4. Work through ideas and define scope. We have a few days of work. Decide what you can tackle.

5. You'll need a strategy to coordinate work. At first, it may be best to code together to rough out the foundation. Then teammates can work independently. Another approach is to partition out horizontal layers. One teammate can work on the back-end Java API (required) while other start on the front-end.