---
name: code-review
description: Use when you want the user asks you to review a pull request or some code.
---

# General Task

When this skill is invoked it means that the user wants you to start a code review on an open pull request. You will act as a senior software engineer, reviewing the code changes and then providing valuable and actionable feedback on the code.

## 1. Gather Information

In order to sucessfully complete this task you first must ask the user what should be reviewed. Ask the user to either provide you with a link to an open pull request.

Additionally this pull request might be related to a spefific ticket. Ask the user to provide you with the link to the completed ticket, if appropriate.

## 2. Gather Context

Once you have completed step 1, you need to gather context on both the requirements and the implementation. 

First: Read the provided ticket or task context the user provided you with fully and understand the intent of the task at hand.

Second: Read all changes that were made in the linked pull request. Understand what the pull request wanted to achieve and how the code changes helped getting there.

## 3. Create a Review

Once step 2 is completed you should have a full idea of what the PR was meant to implement, how it was trying to achieve this and what the actual code changes are. You must then start the review.

Focus on the following topics:

| Topic             | Description                                                                                                                            |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| Security          | do the code changes expose any sensitive data, such as keys, credentials, or PII data.                                                 |
| Bugs              | do the code changes introduce any unexpected behaviour, or affect previous logic such that the methods now produce a different outcome |
| Test coverage     | are the new code changes covered by automated tests                                                                                    |
| Clean code        | are the code changes unnecessarily verbose, convoluted or nested                                                                       |
| Style consistency | do style conventions match existing code patterns in the code base                                                                     |
| Code duplication  | do the changes introduce avoidable code duplication                                                                                    |

If you find any concerns with any of these topics, create a concise, clear and actionable comment for the exact section of the code. Then create a pull request review comment directly on the pull request.

If you decide to create a pull request comment the impact of the requested change should be medium to high. For nitpicky change requests no individual comments should be created.

After creating each direct review comment, create one brief summarization on the whole PR, indicating if you believe the feature was implemented correctly and completely.
