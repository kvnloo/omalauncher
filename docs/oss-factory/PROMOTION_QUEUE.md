<!-- oss-factory:v1
{"id":"20261018-omalauncher-canonical-promotion-queue","kind":"state","project":"omalauncher","from":"codex","to":["factory"],"status":"working","civ":{"city_id":"oss:omalauncher"},"dedupe_key":"promotion-queue:SimonSchubert/omalauncher"}
-->

# Omalauncher canonical promotion queue

Origin: https://github.com/SimonSchubert/omalauncher  
Owning fork: https://github.com/kvnloo/omalauncher  
Canonical document: https://github.com/kvnloo/omalauncher/blob/coordination/promotion-queue/docs/oss-factory/PROMOTION_QUEUE.md

This is the one owning-fork queue, established by the scoped promotion coordinator after searching all fork issues, PRs, branches, and documentation. Issues are disabled. This coordination branch and its queue PR must remain available for continuing work; neither is an implementation merge request. Identity `omalauncher` / `oss:omalauncher` is reused from the existing committed [registration proposal 111](https://github.com/kvnloo/oss-factory/pull/111), not asserted main-merged. No physical topology placement is invented.

## Authorization and current lease

Direct operator scope: reconcile already-accepted Copilot work, retain its original evidence/credit, close only eligible fork PR 1 without merging or deleting its branch, and establish this missing owner-local queue. No source/workflow changes, origin write, new readiness, device action, or default-branch merge is authorized.

- Claim: https://github.com/kvnloo/omalauncher/pull/1#issuecomment-6247553908
- Owner: codex, bounded accepted-work reconciliation for `SimonSchubert/omalauncher#129`.
- Lease: **released** after accepted-work reconciliation on 2026-10-18. Other contribution claims were not acquired.

## Ready

None verified ready by this coordination-only audit.

## New work — 2026-10-20

| Source / carrier | Exact head / status | Next gate |
| --- | --- | --- |
| [Hermes agent setup QA PR 8](https://github.com/kvnloo/omalauncher/pull/8); original [DeseretSaint origin PR 85](https://github.com/SimonSchubert/omalauncher/pull/85) | `feat/hermes-installer-setup` at `45ad1c5c71be7fe1adb3b1476b6e0ced2ecc4626`. Existing contributor's Hermes implementation plus fork-only first-run/setup checks. **Draft / CI and signed phone install unverified**. | Await own hosted CI, review proot installer fork/provenance and actual handset login/install; no duplicate origin PR. |
| [Drawer keyboard QA PR 9](https://github.com/kvnloo/omalauncher/pull/9); origin [issue 178](https://github.com/SimonSchubert/omalauncher/issues/178) by haxx0071 | `fix/optional-drawer-keyboard-178` at `ec8f90a2d790a9e6211c2c69f07dce17fb919001`, based on upstream main `53505248b9f8616fe308c4972cbbf2f6693a6946`. Four files: default-on flag, style UI, focus behavior, TOML regression test. **Draft / hosted CI started, not yet verified**. | Check exact-head `./gradlew :owl:test :app:testDebugUnitTest :owl:owlJar`, then seek upstream review without duplicate; physical IME acceptance still pending. |

Keep both QA carriers separate from this **one canonical coordination queue**. Neither carrier approves an origin merge, claims physical installation, or replaces the original contributor's credit.

## Existing upstream review / needs proof / held

The following original carriers are indexed, not closed or taken over. Their linked records retain authorship, evidence, comments and historical limitations. Their current origin outcomes are not claimed by this narrow audit.

| Original fork carrier | Source identity observed 2026-10-18 | Preserved context / next gate |
| --- | --- | --- |
| [2: dictation CI](https://github.com/kvnloo/omalauncher/pull/2) | `fix/ime-voice-commit` at `033fc312cf32bca0a88100654090ad12489d0382` | Existing [origin 38](https://github.com/SimonSchubert/omalauncher/pull/38), [legacy QA 112](https://github.com/kvnloo/oss-factory/issues/112): preserve physical/signed-compatible-device gate; no new native proof claimed. |
| [3: multi-touch CI](https://github.com/kvnloo/omalauncher/pull/3) | `fix/wayland-multitouch` at `06272b8aeb000a9ff4d667a3632c5eeb148bf7fe` | Existing [origin 40](https://github.com/SimonSchubert/omalauncher/pull/40), [legacy QA 113](https://github.com/kvnloo/oss-factory/issues/113): retain native contact/cancellation and Tern landing-order context. |
| [4: terminal lifecycle CI](https://github.com/kvnloo/omalauncher/pull/4) | `fix/terminal-tile-autostart` at `22249bc6ce875eb0c7624673b6102535492930ca` | Existing [origin 130](https://github.com/SimonSchubert/omalauncher/pull/130), [legacy QA 116](https://github.com/kvnloo/oss-factory/issues/116): JVM lifecycle evidence is not physical Android paging/background survival. |
| [5: Tern finger-scroll CI](https://github.com/kvnloo/omalauncher/pull/5) | `fix/tern-touch-keyboard` at `6dcd85392a8c50bf82e3257ecb4455c3883e9270` | Existing [origin 128](https://github.com/SimonSchubert/omalauncher/pull/128), [legacy QA 114](https://github.com/kvnloo/oss-factory/issues/114): preserve installed updated-APK/native routing proof boundary. |
| [6: OpenCode install candidate](https://github.com/kvnloo/omalauncher/pull/6) | `fix/opencode-v2-install` at `a47f92bd98766bfbd92b36c86b83025e40d82287` | Existing [origin issue 145](https://github.com/SimonSchubert/omalauncher/issues/145), [legacy QA 183](https://github.com/kvnloo/oss-factory/issues/183): fork-only evidence and original authorization limits remain; no publication or runtime change here. |


## October 20, 2026 — new work and origin-promotion gate

This section records a new user-directed development wave. It does **not** retroactively broaden the bounded October 18 coordinator lease.

- **Drawer browsing without forced keyboard** ([upstream issue #178](https://github.com/SimonSchubert/omalauncher/issues/178)): new fork branch [`fix/drawer-keyboard-focus-178`](https://github.com/kvnloo/omalauncher/tree/fix/drawer-keyboard-focus-178), at `aa79d7b11368ae2b2a75e36b440ac1cefcd2728b`, based exactly on upstream `53505248b9f8616fe308c4972cbbf2f6693a6946`. Two files changed: `Gestures.kt` saves an opt-in/out "drawer keyboard" choice (default remains "on open"), and `PromptBar.kt` only auto-focuses on drawer open when allowed; an explicit bar tap still focuses. **Status: HOLD for hosted CI, Android acceptance, and upstream publication.** A scoped upstream create-PR attempt on October 20 returned HTTP 403 `Resource not accessible by integration`; no origin PR exists from this attempt. Do not claim CI passed or phone tested. Next: use an authorized `gh` session to create the PR against `SimonSchubert/omalauncher:main`, then obtain exact-head CI and on-device swipe/tap verification.
- **Existing origin PR #158** ([live-window recovery](https://github.com/SimonSchubert/omalauncher/pull/158), fork `fix/live-window-recovery` @ `802f243bc59be4e9429710ac66b6e22b65d1801f`): open; recorded origin body and comment state four S25 Ultra instrumentation scenarios passed with isolated test installs. The current origin PR metadata reports `mergeable=false`. Next: identify and resolve the actual mainline conflict and re-run checks; do not treat prior device evidence as validation of any conflict resolution.
- **Existing origin PR #85** ([Hermes agent and user-provided commands](https://github.com/SimonSchubert/omalauncher/pull/85), by DeseretSaint): open, independently includes Hermes install/prompt routing, alias/icon and a custom command path. Fork branch `feat/hermes-installer-setup` exists, but overlapping work must be deduplicated or reduced to a distinct, reviewable delta rather than submitted as a competing Hermes PR.

No new issue or duplicate promotion queue was created. The existing owner-local coordination PR #7 remains the only canonical queue carrier.

## Landed

### Copilot interactive prompt — source acceptance verified

- Original [fork CI carrier 1](https://github.com/kvnloo/omalauncher/pull/1); [origin PR 129](https://github.com/SimonSchubert/omalauncher/pull/129), author kvnloo; requirement [origin issue 126](https://github.com/SimonSchubert/omalauncher/issues/126).
- Fork branch and current origin source head: `fix/copilot-interactive-prompt` at `7bd3951082b18b18aecf4d334970521ac2e8961b`.
- Origin is **MERGED**, merged by SimonSchubert at `2026-10-16T21:35:24Z`; accepted merge: [`edab9fba94d2d491b8ef474aa74e5715cae7db18`](https://github.com/SimonSchubert/omalauncher/commit/edab9fba94d2d491b8ef474aa74e5715cae7db18).
- Full source-head and merge trees both equal `d43129b4abb5f479e70ce830ec7daba24bcd65ca`: all final accepted files are represented. The carrier has an older comparison base (71 files versus the final origin's 2); that historical comparison is not 71 new accepted changes.
- [Maintainer acceptance](https://github.com/SimonSchubert/omalauncher/pull/129#issuecomment-6225151084) records the rebase and two deliberate changes: retain `everyAgentHasAWindowAlias` and omit per-agent README detail. Preserve these decisions rather than restore obsolete proposed text.
- Historical [hosted CI](https://github.com/kvnloo/omalauncher/actions/runs/38692310927) covers older `295d17f06d2d4f57927456359c28dca66cad1bc2`, not the final rebased head. It remains app/OWL/OWL-JAR and CLI-parser evidence, **not** authenticated Copilot chat, physical-device acceptance or APK validation. No tests were run for this metadata-only reconciliation.
- Full carrier/origin comments, submitted reviews and inline threads were read. No ongoing request requires this carrier to stay open; the recorded instruction is to keep it **unmerged**, which closure without merge preserves. The canonical queue is this separate document/PR, not carrier 1.
- Disposition: **LANDED**; [substantive exact-head receipt](https://github.com/kvnloo/omalauncher/pull/1#issuecomment-6247570070). Fork carrier 1 was **CLOSED, not merged**, at `2026-10-18T08:20:31Z`. Its branch was observed **retained after closure** at exact `7bd3951082b18b18aecf4d334970521ac2e8961b`. Original PR, commits, authorship and historical evidence remain. This is historical reconciliation, not a new origin outcome.

## 2026-10-20 verification delta — no duplicate upstream PRs

The earlier coordination-only "Ready: none" is a historical snapshot, not a statement that later CI runs failed. This section indexes work verified against exact Git refs; it does not grant merge authority or imply phone installation.

| Work | Evidence verified October 20 | Next promotion gate |
| --- | --- | --- |
| [Hermes setup / fork PR #8](https://github.com/kvnloo/omalauncher/pull/8) | Initial integration head `45ad1c5c71be7fe1adb3b1476b6e0ced2ecc4626` passed [hosted tests, debug build and lint](https://github.com/kvnloo/omalauncher/actions/runs/39376700131) (job `118476337471`). The branch advanced after this run, so that green result **does not verify later heads**. Attribution/source: [DeseretSaint's existing upstream PR #85](https://github.com/SimonSchubert/omalauncher/pull/85), still open; its authored branch and device report are preserved. | Recheck CI on the final exact head; verify proot install, login/prompt flow and APK on a separate/compatible installation. Do **not** open a competing upstream Hermes PR while #85 is pending. A reviewer should evaluate the stated third-party Hermes compatibility-fork installer before release. |
| [OpenCode 2 installer / fork PR #6](https://github.com/kvnloo/omalauncher/pull/6) | Head `a47f92bd98766bfbd92b36c86b83025e40d82287` passed [hosted CI](https://github.com/kvnloo/omalauncher/actions/runs/38936440363), in addition to the existing documented ARM64 CLI checks. But [upstream issue #145](https://github.com/SimonSchubert/omalauncher/issues/145) is **closed**: SimonSchubert's [October 16 response](https://github.com/SimonSchubert/omalauncher/issues/145#issuecomment-6225016106) describes mise refresh and Node provisioning, now present in upstream main. Comparison with upstream main `53505248b9f8616fe308c4972cbbf2f6693a6946`: fork branch is 1 ahead, 86 behind. | Reproduce any *remaining* difference against **current** upstream behavior before upstream publication; avoid reimplementing an already-closed bug. No physical Android update or authenticated session is claimed. |
| [Window recovery / upstream PR #158](https://github.com/SimonSchubert/omalauncher/pull/158) | Open at `802f243bc59be4e9429710ac66b6e22b65d1801f`; [hosted CI passed](https://github.com/SimonSchubert/omalauncher/actions/runs/38998620655) and the PR documents **four physical Galaxy S25 Ultra instrumentation scenarios passed** in an isolated test app. No reviewer approval found at this check. | Ready for maintainer review, **not merged**. Preserve the existing Play-signed installation and do not represent tests of the isolated APK as deployment of the feature to the live build. |

Other origin carriers [#38](https://github.com/SimonSchubert/omalauncher/pull/38), [#40](https://github.com/SimonSchubert/omalauncher/pull/40), [#128](https://github.com/SimonSchubert/omalauncher/pull/128), and [#130](https://github.com/SimonSchubert/omalauncher/pull/130) remained open during this audit; their existing physical-signing/gesture/lifecycle gates remain. [#129](https://github.com/SimonSchubert/omalauncher/pull/129) remains the verified upstream merge. No origin PR, default-branch merge, maintainer ping, or device installation is performed by this queue update.

## Lossless legacy routing and credit

The full original bodies, comments, attribution, evidence and past claims remain unchanged at these linked archival sources; no legacy thread is deleted or silently rewritten:

- [Prior promotion queue 173 and all comments](https://github.com/kvnloo/oss-factory/issues/173), including [bounded maintenance claim](https://github.com/kvnloo/oss-factory/issues/173#issuecomment-6219456284) and [completed maintenance result](https://github.com/kvnloo/oss-factory/issues/173#issuecomment-6220994173).
- [Original workstream 110 and all comments](https://github.com/kvnloo/oss-factory/issues/110), [Copilot QA 115 and all comments](https://github.com/kvnloo/oss-factory/issues/115), [registration/runbook proposal 111](https://github.com/kvnloo/oss-factory/pull/111).
- Peer-review provenance remains [QA 117](https://github.com/kvnloo/oss-factory/issues/117) for [origin 66](https://github.com/SimonSchubert/omalauncher/pull/66) and [QA 118](https://github.com/kvnloo/oss-factory/issues/118) for [origin 58](https://github.com/SimonSchubert/omalauncher/pull/58); no peer candidate is duplicated or reclassified here.

Legacy references to Linear or factory-local routing are historical, not current authority. Explicit human instructions and the current factory control/HITL policy govern new actions. The parent coordinator owns the registry/directory redirect; continuing project detail belongs here. No broad project queue-clean or merge-readiness claim is made.
