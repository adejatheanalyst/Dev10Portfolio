# Java Fundamentals

This repository is a template for creating a classwork repository. The template contains starter code for exercises, assessments, and lesson content. It is required for the course.

## Create a classwork repository

1. Visit https://github.com/new to create a new repository.
2. Under the "Repository template" label, select the `dev10-program/java-fundamentals-student-template` repository template.
3. Select your GitHub account name in the "Owner" select list.
4. Name your repository `dev10-classwork`.
5. Toggle the radio button to "Private".
6. Clicking the "Create repository" button will to create a `dev10-classwork` repository in your Github account.

> For more information about creating a repository from a template, see https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template.

### Adding Instructors as collaborators

After creating your repository, complete the following steps to add your instructors as collaborators.

1. Visit the home page for your `dev10-classwork` GitHub repository.
2. Browse to the "Settings" tab.
3. On the left, click on "Manage access".
4. Click on the "Invite a collaborator" button.
5. In the popup window, type your instructor's GitHub account name.
6. Click the "Add {account name} to this repository" button to complete the process.
7. Repeat steps #4-6 to add additional instructors as collaborators.

## Cloning Your Repo

Before you begin working on your classwork, you need to clone your GitHub repository to your local machine.

1. Open a terminal window and use the `cd` command to browse to the folder where you want to clone your repository.
2. Use the `git clone` command to clone your repository to your local machine:

```
git clone https://github.com/{account name}/dev10-classwork.git 
```

> Note: replace `{account name}` with your GitHub account name. You can copy the URL to your GitHub repo to the clipboard by clicking on the "Code" button from your repo's home page and then clicking the clipboard button to the right of your repo's URL.

After Git has completed cloning the repository to your local machine, you can use IntelliJ to open any of the projects contained within the repository.

**Important: Don't attempt to open the entire repository in IntelliJ! Only open a single project's folder at a time.**

## Creating and Pushing a Commit

To backup ongoing work or to share work with your instructors, complete the following steps to create and push a commit:

1. Use the `cd` command to browse to your repository's folder (e.g. `cd dev10-classwork`).
2. From the terminal, use the `git add --all` command to stage all of your uncommitted changes.
3. Use the `git commit -m "Latest code changes"` command to create a commit.
  * Avoid the temptation to always use a generic commit message like "Latest code changes".
  * Using a specific commit message, such as "Update variables and types exercises", will make it easier to locate a specific commit when reviewing the history for your repository.
4. Use the `git push` command to push your new commit to GitHub.
5. Browse to your repository on GitHub and confirm that your changes are available within the remote repository.
