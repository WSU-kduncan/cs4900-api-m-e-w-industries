### What changed (summary)

- Fixed a mismatch between our SQL and the actual DB columns for **Matches**.
- Updated the repo query to use the real columns from `Matched_User`:
  - `User_Id` (was wrongly using `User`)
  - `Liked_User_Id` (was wrongly using `Liked_User`)
- Dropped the backticks around `User` and stopped using the reserved word altogether.
- Lined up the entity with the table:
  - `MatchedUser` now uses `@JoinColumn(name = "User_Id")` and `@JoinColumn(name = "Liked_User_Id")`
  - `Is_Matched` is mapped as a `Boolean`.
