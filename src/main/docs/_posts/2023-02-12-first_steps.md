---
title:  "DEV_LOG: first steps"
date:   2023-02-12 08:06:17 +0100
categories: log
---

[<- Back to top](/)

Setting up a new repository is not always here, so here is an outline of the first steps I tool in order to
make sure I was set up for mostly hassle-free coding in the future.

In general, you want to set to set up your project outline, documentation outline, and core automations up before you start writing code.
The reasoning is quite simple:

- It is easier to set this up from the get go, rather than get distracted by it later on when it can have an impact
  on your code.
- Why not make use of the code-improvement tooling from the start?

Here is an outline of how I went about setting everything up.
For technical details, you can always take a look at the commit history on
the [github project](https://github.com/sddevelopment-be/daydash/commits/main).

## Day 1

- Main focus on the project automation, adding a CI pipeline, and static code analyses
- Structured the projects layers and maven dependency relationships
    - the goal here is to make sure the `core` module only has incoming dependencies on other `daydash` modules (except maybe fore the `testing`
      module).
    - To illustrate how an isolated module can still interface with other modules, I implemented a simple domain (only an Action for now) and a
      simple RepositoryService.
- Messing around with the code coverage configuration, as I made a mistake somewhere int he SonarQube set-up

## Day 2

- Add some documentation-related stuff, mostly to demonstrate minimalist documentation set-ups.
    - Use of PlantUML, Markdown, and Jekyll (intended
      for [github pages](https://docs.github.com/en/pages/setting-up-a-github-pages-site-with-jekyll/about-jekyll-build-errors-for-github-pages-sites))
- Moved the documentation into a separate part of the root `src/main` directory.