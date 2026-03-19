---
name: code-review
description: Use when asked to review a pull request — fetches PR diff and ticket context, evaluates code quality across six dimensions, and posts inline review comments to GitHub.
---

# Code Review

Act as a senior software engineer conducting a thorough, actionable PR review.

## 1. Gather Information

If a PR URL and ticket path were passed as arguments, use them directly. Otherwise ask the user for:
- A link to the open pull request
- Optionally, the path or link to the related ticket

## 2. Gather Context

1. Read the ticket fully — understand the intent and Definition of Done
2. Fetch the PR diff (`gh pr view <N> --repo ... && gh pr diff <N> --repo ...`) — understand what was changed and why

## 3. Review

Evaluate across these dimensions:

| Topic             | What to check                                                                 |
|-------------------|-------------------------------------------------------------------------------|
| Security          | Exposed secrets, credentials, or PII                                          |
| Bugs              | Unexpected behaviour, broken existing logic                                   |
| Test coverage     | New code covered by automated tests                                           |
| Clean code        | Unnecessary verbosity, deep nesting, convoluted logic                         |
| Style consistency | Matches existing conventions in the codebase                                  |
| Code duplication  | Avoidable duplication introduced                                              |

Only create inline PR comments for **medium-to-high impact** findings. Skip nitpicks — mention them in the summary instead.

## 4. Post to GitHub

Use `gh api` to submit a review. Always prefix with `GH_TOKEN=$CLAUDE_GH_TOKEN` to post as the configured GitHub App identity:

```sh
GH_TOKEN=$CLAUDE_GH_TOKEN gh api repos/{owner}/{repo}/pulls/{n}/reviews \
  --method POST \
  --field event="COMMENT" \
  ...
```

If `CLAUDE_GH_TOKEN` is not set, fall back to default `gh` credentials and inform the user comments will appear under their personal account.

**Note:** Use `event="COMMENT"` — `REQUEST_CHANGES` is not allowed on your own PRs.

## 5. Summary

After posting inline comments, post one overall summary comment stating whether the feature was implemented correctly and completely, and listing any nitpicks not worth individual comments.

## Common Mistakes

- Using `REQUEST_CHANGES` on a PR you own → GitHub rejects it with 422; use `COMMENT`
- Commenting on deleted lines without specifying `side="LEFT"` → wrong position
- Forgetting `GH_TOKEN=$CLAUDE_GH_TOKEN` → comments post as personal account
- Asking for PR/ticket when they were already passed as arguments
