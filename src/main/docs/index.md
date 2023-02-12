---
title: DayDash
---

# Goal

The functional goal of DayDash is to be a portable personal task-manager and organizer.
Inspired by initiatives such as [ToDo.txt](http://todotxt.org/) and [the Solid project](https://solidproject.org/),
we wish to make the system operate as a SaaS solution, while allowing users to swap out the application in favor of other solutions in the same
space without the need for them to convert their data manually.

![depiction of intended user flow](./design/task_flow.png)

# Design

Outline of the technical choices, and their reasons.
[design principles](./design/README.md)

# Implementation Roadmap

more details are available in the: [Developer Log](./operational/DEV_LOG.md).
DayDash is intended as an excercise in lean programming techniques.
As such, we will follow a automation-driven approach with minimal brain-to-screen barriers.

## DEV LOG Journal

<ul>
    {% for doc in site.posts %}
        <li><a href="{{ doc.url }}">Entry on {{ doc.date | date: "%-d %B %Y" }}: <b>{{ doc.title }}</b></a></li>
    {% endfor %}
</ul>
