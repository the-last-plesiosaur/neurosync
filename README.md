# Neurosync

Neurosync is a spaced-repetition learning system with an in-universe cyberpunk theme. 
The application is responsible for:

- creating and opening Vaults
- creating and editing Shards
- organizing content with Keys
- running Signal Jack review sessions
- viewing review and fragmentation-related stats

## Domain Concepts

### Vault

A Vault is the top-level container for spaced repetition content. Vaults are saved as files. 
It replaces the traditional “Deck.”

- Owns a collection of Shards
- Tracks overall Vault Fragmentation (health/decay of the Vault)
- Provides Vault Fragmentation, aggregate review/statistics information
    * total Shards
    * queued Shards
    * fragmentation score (percentage based on the above)

### Shard

A Shard is the atomic reviewable unit. It replaces the traditional “Card.”

- Each Shard contains:
    - Challenge (question/prompt)
    - Response (answer/expected recall)
    - Key (a label for organizing Shards)
- Shards are scheduled using a decay-driven model:
    - Decay Window: how long before recall degrades (in days)
    - Next Jack: the next time this Shard is due for review (“queued”)
- Neural Integrity: a percentage indicating how stable the Shard is based on reviews
    * Stabilized increases integrity (up to a cap)
    * Corrupted decreases integrity (down to a floor)
- Lifecycle
    - Cold Storage (archived; excluded from normal queue)

### Signal Jack

A Signal Jack is a review session. It replaces a traditional review “run” or “study session.”

- Starts either globally (across all Shards in the Vault) or for a specific Key
- Displays the next queued Shard
- Depending on response to Shard
    - Increases the decay window for Stabilized outcomes
    - Decreases the window for Corrupted outcomes
    - Schedules Next Jack depending on new decay window

### Domain Glossary

* Vault: container of Shards
* Shard: Reviewable unit (Challenge/Response)
* Challenge: Prompt/question for recall
* Response: Expected answer
* Decay Window: Interval before next review
* Neural Integrity: Mastery / stability percentage
* Next Jack: Scheduled date for next review
* Signal Jack: Review session
* Stabilized: Correct outcome
* Corrupted: Incorrect outcome
* Vault Fragmentation: Vault-level stats/health roll-up
* Cold Storage: Archived Shards excluded from routine queuing