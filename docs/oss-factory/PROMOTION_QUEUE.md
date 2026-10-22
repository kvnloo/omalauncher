<!-- oss-factory:v1
{"id":"20261018-omalauncher-canonical-promotion-queue","kind":"state","project":"omalauncher","from":"codex","to":["factory"],"status":"working","civ":{"city_id":"oss:omalauncher"},"dedupe_key":"promotion-queue:SimonSchubert/omalauncher"}
-->

# Omalauncher canonical promotion queue

Origin: https://github.com/SimonSchubert/omalauncher  
Owning fork: https://github.com/kvnloo/omalauncher  
Canonical document: https://github.com/kvnloo/omalauncher/blob/coordination/promotion-queue/docs/oss-factory/PROMOTION_QUEUE.md

This is the one owning-fork queue, established by the scoped promotion coordinator after searching all fork issues, PRs, branches, and documentation. Issues are disabled. This coordination branch and its queue PR must remain available for continuing work; neither is an implementation merge request. Identity `omalauncher` / `oss:omalauncher` is reused from the existing committed [registration proposal 111](https://github.com/kvnloo/oss-factory/pull/111), not asserted main-merged. No physical topology placement is invented.

<!-- promotion-update:2026-10-22:terminal130-market-api29 -->
## October 22 — current-head terminal #130 CI and independent Android lint fix

- **Origin [#130 terminal tile restart](https://github.com/SimonSchubert/omalauncher/pull/130)**: existing source/docs branch reconciled with origin `main@87938b8d` using a non-force two-parent merge, published head [`2aaa20f0`](https://github.com/kvnloo/omalauncher/commit/2aaa20f0be6c06e96bbc8af830c65a6824d17bd8). GitHub comparison is **4 ahead / 0 behind**, exactly the original two changed files, and the origin PR is OPEN / non-draft / mergeable. [Exact-head CI 39860262624](https://github.com/SimonSchubert/omalauncher/actions/runs/39860262624) **FAIL**: `Test` step PASS; `Build and lint` FAIL at `app/src/main/kotlin/dev/omalauncher/widgets/Market.kt:269`, an API-31-only `BitmapRegionDecoder.newInstance` call already present on origin `main` (app `minSdk=29`). The terminal patch does **not** change Market.kt. **Disposition: ORIGIN_LIVE / exact-head CI blocked by unrelated origin-main lint**, not green or maintainer-accepted. Physical Android paging on this merged head NOT RUN.
- **Isolated dependency repair [fork PR #13](https://github.com/kvnloo/omalauncher/pull/13)** at [`e66acd8a`](https://github.com/kvnloo/omalauncher/commit/e66acd8a061d46c7b913adb3bfb506f908230cab): one-line change selecting Android's four-argument decoder overload (API 10+, `isShareable` ignored) with a 1-ahead/0-behind diff against exact current-origin snapshot branch `sync/omalauncher-upstream-87938b8`. Its [exact-head fork CI 39861423925](https://github.com/kvnloo/omalauncher/actions/runs/39861423925) has **SUCCEEDED at exact head**: app and OWL tests, OWL JAR, debug APK build and Android lint PASS. This confirms the API-29 compatibility fix in the isolated fork branch; physical handset decoding NOT RUN, origin PR/submission NOT CREATED. This is a distinct fork-local candidate, **not** a new origin PR or #130 scope expansion. Separate scoped HITL and available origin submission capability remain required; previous unrelated origin create-PR 403 is not a green light to retry denied actions.
- Preserve original upstream source and contributor credit. Do not merge fork/default branches, add an unrelated `Market.kt` hunk to #130, or treat earlier #130 CI success at old head `3e7d0e3` as current-head proof.

## Authorization and current lease

Direct operator scope: reconcile already-accepted Copilot work, retain its original evidence/credit, close only eligible fork PR 1 without merging or deleting its branch, and establish this missing owner-local queue. No source/workflow changes, origin write, new readiness, device action, or default-branch merge is authorized.

- Claim: https://github.com/kvnloo/omalauncher/pull/1#issuecomment-6247553908
- Owner: codex, bounded accepted-work reconciliation for `SimonSchubert/omalauncher#129`.
- Lease: **released** after accepted-work reconciliation on 2026-10-18. Other contribution claims were not acquired.

## Active Hermes integration (2026-10-20)

- Fork QA [PR #8](https://github.com/kvnloo/omalauncher/pull/8), branch `feat/hermes-installer-setup`. Distinct from [original upstream PR #85](https://github.com/SimonSchubert/omalauncher/pull/85) by **DeseretSaint**; integration retains the original branch as a merge parent, and must not become a competing upstream PR.
- First exact head `45ad1c5c71be7fe1adb3b1476b6e0ced2ecc4626` passed [GitHub Actions CI](https://github.com/kvnloo/omalauncher/actions/runs/39376700131) (app and OWL tests, build, lint).
- Hardened branch head `dd829d108d969cef9455c8d694cc09a195c4b9b9` propagates installer curl failures and preserves existing `uv.toml`; shell subprocess regressions cover successful and failed mock downloads. **Exact-head [CI PASS](https://github.com/kvnloo/omalauncher/actions/runs/39438039870)**: OWL/app tests, OWL JAR, debug APK build and Android lint. Earlier intermediate test-fixture failures were corrected before this head.
- **HOLD / native gate:** fork's third-party proot compatibility dependency must be reviewed, and a real Arch/proot install, CLI prompt and Android APK still need verification. Existing uv config may require explicit copy link mode for standalone guest use. No upstream promotion or actual device validation claimed.

## Upstream promotion blockers refreshed 2026-10-20 (historical pre-rebase snapshot; superseded by the verified head tables below)

- **Merge conflicts:** [#38 dictation](https://github.com/SimonSchubert/omalauncher/pull/38), [#40 multitouch](https://github.com/SimonSchubert/omalauncher/pull/40), [#130 terminal lifecycle](https://github.com/SimonSchubert/omalauncher/pull/130), and [#158 live-window recovery](https://github.com/SimonSchubert/omalauncher/pull/158) all currently report `mergeable_state=dirty`. Reconcile against current upstream first, particularly the October 19 home/OWL panel changes; do not assume older test results prove the new merge tree.
- **Upstream [#128 Tern gestures](https://github.com/SimonSchubert/omalauncher/pull/128):** `mergeable=true` but `mergeable_state=unstable`, with no submitted reviews. Existing fork [CI proof](https://github.com/kvnloo/omalauncher/actions/runs/38853703975) concerns an older verified head and is not physical-device gesture acceptance. Prioritize native Android/Tern proof and an exact current-head check.
- **DeseretSaint's original [#85 Hermes](https://github.com/SimonSchubert/omalauncher/pull/85):** also reports `dirty`. Retain original author ownership, offer verified fixes upstream after fork QA rather than opening a duplicate.
- These are current mergeability reads, **not** maintainer rejections or new permission to force-push/rebase someone else's work.

## Ready

None verified ready by this coordination-only audit.

## New work — 2026-10-20

| Source / carrier | Exact head / status | Next gate |
| --- | --- | --- |
| [Hermes agent setup QA PR 8](https://github.com/kvnloo/omalauncher/pull/8); original [DeseretSaint origin PR 85](https://github.com/SimonSchubert/omalauncher/pull/85) | `feat/hermes-installer-setup` at `45ad1c5c71be7fe1adb3b1476b6e0ced2ecc4626`. Existing contributor's Hermes implementation plus fork-only first-run/setup checks. **Draft / CI and signed phone install unverified**. | Await own hosted CI, review proot installer fork/provenance and actual handset login/install; no duplicate origin PR. |
| [Drawer keyboard QA PR 9](https://github.com/kvnloo/omalauncher/pull/9); origin [issue 178](https://github.com/SimonSchubert/omalauncher/issues/178) by haxx0071 | `fix/optional-drawer-keyboard-178` at `34abc6bbb67755942579ba68f29cce51841eae5b`, based on upstream `53505248b9f8616fe308c4972cbbf2f6693a6946`. Five intended files: default-on style flag, settings toggle, focus policy, TOML and focus-transition JVM tests. **LIMITED / exact-head CI PASS**: [run 39445522065](https://github.com/kvnloo/omalauncher/actions/runs/39445522065) — JVM tests, debug build and lint success Oct 20, 2026; physical IME unverified. | Maintainer review after separately authorized origin publication; reproduce tap-only and default behavior on an isolated physical APK first if possible. Connector-origin PR creation received 403; no source publication claim. |

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

## Work added October 20, 2026 (fork QA, not upstream admission)

| Carrier | Source | Status / next gate |
| --- | --- | --- |
| [8: Hermes setup](https://github.com/kvnloo/omalauncher/pull/8) | `feat/hermes-installer-setup` built from [DeseretSaint's upstream PR 85](https://github.com/SimonSchubert/omalauncher/pull/85) | Draft fork QA; installer failure-status regression added during this wave. Preserve DeseretSaint's authorship and update existing origin PR rather than creating a competing one. Exact-head hosted CI [PASS at `dd829d1`](https://github.com/kvnloo/omalauncher/actions/runs/39438039870) (app/OWL tests, APK build, lint); physical proot/authenticated-agent install remains pending. No updated APK installed. |
| [10: drawer opacity](https://github.com/kvnloo/omalauncher/pull/10) | `feat/drawer-opacity-171`, baseline origin `5350524`; [issue 171](https://github.com/SimonSchubert/omalauncher/issues/171) by shafiqimtiaz | Existing fork draft; new Style > app drawer Clear/Default/Solid presets plus `drawer.opacity` 0..1 (default 0.97), unit tests and docs at [`063b4ec`](https://github.com/kvnloo/omalauncher/commit/063b4ec022821fd343617efc456f40f8386b9d23). Exact-head hosted CI **PASS at `063b4ec`** ([run 39444389290](https://github.com/kvnloo/omalauncher/actions/runs/39444389290)): app/OWL tests, OWL JAR, debug APK build and lint. Physical visual validation remains pending. App not installed; actual appearance unverified. One upstream PR creation attempt returned GitHub connector **403: Resource not accessible by integration**; no origin PR was created, and no bypass/retry is planned. |

Both carriers stay separate from the original coordinator's historical October 18 reconciliation lease. Fork CI success alone does not authorize upstream publication.

## Hermes installer and setup QA — 2026-10-20

- **Original upstream carrier:** [DeseretSaint's #85](https://github.com/SimonSchubert/omalauncher/pull/85), still open; preserve this author's original implementation, branch and credit. **Do not open a duplicate upstream Hermes PR.**
- **Downstream current-main QA:** [kvnloo/omalauncher#8](https://github.com/kvnloo/omalauncher/pull/8), draft; `feat/hermes-installer-setup` integrates #85 through a merge parent onto upstream Oct 19 base `542c021bf4c1200162e4cf99bad34cd0264dcc22`.
- Installer regression: an unavailable download previously led to a successful empty Bash execution (curl exit 22, overall status 0). Updated flow downloads to a temporary file and runs only after successful fetch, cleaning up afterward. Existing UV configuration is retained; new guests get copy link mode.
- Exact-head CI [run 39438039870](https://github.com/kvnloo/omalauncher/actions/runs/39438039870) for `dd829d108d969cef9455c8d694cc09a195c4b9b9`: **full CI passed**, including app/OWL tests, OWL JAR, Android debug APK assembly and lint. Intermediate failing commits are superseded; this is not a physical phone test.
- Gates: verify full exact-head CI, check installer provenance and the DeseretSaint compatibility fork, and validate on an isolated/signed-compatible Android build before claiming this implementation works on the user's installed phone. No APK was installed or production Tern session replaced.
- Promotion path: send the minimal installer reliability findings and tests to #85's author/maintainer, without independently posting a competing upstream PR.

## Origin PR landing support — 2026-10-20

- [Origin #158: live Linux-window recovery](https://github.com/SimonSchubert/omalauncher/pull/158) — **mergeable, nondraft**, head `6894e69` (0.10.0 integration), [exact-head origin CI PASS](https://github.com/SimonSchubert/omalauncher/actions/runs/39439401520), 4 earlier physical-device instrumentation scenarios passed before the merge-from-main update. No current-head handset rerun or maintainer review recorded. This is the strongest existing upstream merge-review candidate; maintainer decides landing.
- [Origin #130: terminal tile autostart](https://github.com/SimonSchubert/omalauncher/pull/130) — mergeable, nondraft, head `6279a25`, [latest fork CI PASS](https://github.com/kvnloo/omalauncher/actions/runs/39440057389), physical Android paging/lifecycle not yet proven.
- [Origin #128: Tern gestures](https://github.com/SimonSchubert/omalauncher/pull/128) — mergeable, nondraft, head `ddd28f`, [latest CI in progress](https://github.com/kvnloo/omalauncher/actions/runs/39443874883), installed app physical touch-routing still unverified.
- [Origin #38: Linux IME dictation](https://github.com/SimonSchubert/omalauncher/pull/38) and [#40: multi-touch](https://github.com/SimonSchubert/omalauncher/pull/40) — mergeable drafts after Oct 20 main integration, exact-head CI running; native Android acceptance remains pending. Avoid duplicating #128 integration in #40.
- No maintainer-submitted reviews were found on these five PRs at this check. No unsolicited upstream comments, merge commands, or default-branch changes made.

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

### New fork-local QA work (October 20)

- [#9 — optional drawer keyboard](https://github.com/kvnloo/omalauncher/pull/9), addressing [upstream #178](https://github.com/SimonSchubert/omalauncher/issues/178), is a **draft fork-only carrier**. Exact head `ec8f90a2d790a9e6211c2c69f07dce17fb919001` passed [tests, APK compile and lint](https://github.com/kvnloo/omalauncher/actions/runs/39437574713). Device IME behavior unverified; preserve the original reporter haxx0071.
- [#10 — configurable drawer opacity](https://github.com/kvnloo/omalauncher/pull/10), addressing [upstream #171](https://github.com/SimonSchubert/omalauncher/issues/171), is a **draft fork-only carrier**. Exact head `5aea4557af603f6e65db74822edc5c8ce0981589` passed [tests, APK compile and lint](https://github.com/kvnloo/omalauncher/actions/runs/39438225557). Physical rendering not yet verified.
- [#12 — Android active-network/VPN DNS](https://github.com/kvnloo/omalauncher/pull/12), addressing [upstream #20](https://github.com/SimonSchubert/omalauncher/issues/20), is a **draft fork-only carrier**. Exact first head `4c7a4912e3e5793e9761a1b9f0e259102e3c88ce`, [hosted CI](https://github.com/kvnloo/omalauncher/actions/runs/39440269801) **in progress at initial indexing**. DNS changes dynamically in the guest; tests exercise VPN resolvers, suffixes and leak-preventing offline behavior. Preserve original issue contributions (gbenselum/OpenCode) and validate actual VPN/Private DNS behavior on Android before promotion. This does not implement DNS-over-TLS.

These drafts target a pinned upstream-main baseline on the owner fork, and are **not upstream submissions or default-branch merges**. Confirm exact-head CI, Android runtime behavior, deduplication, and reviewer capacity before advancing any.

Other origin carriers [#38](https://github.com/SimonSchubert/omalauncher/pull/38), [#40](https://github.com/SimonSchubert/omalauncher/pull/40), [#128](https://github.com/SimonSchubert/omalauncher/pull/128), and [#130](https://github.com/SimonSchubert/omalauncher/pull/130) remained open during this audit; their existing physical-signing/gesture/lifecycle gates remain. [#129](https://github.com/SimonSchubert/omalauncher/pull/129) remains the verified upstream merge. No origin PR, default-branch merge, maintainer ping, or device installation is performed by this queue update.

## 2026-10-20 origin-head refresh (#158, #130) and drawer keyboard test

This is the current evidence for the new independent user-directed engineering wave. Earlier head values elsewhere in this file are historical snapshots. Preserve those links and the original PR authorship.

| Work | Verified origin/fork state | Remaining gate |
| --- | --- | --- |
| [Upstream #158 — recover live Linux windows](https://github.com/SimonSchubert/omalauncher/pull/158) | Original fork head `802f243` merged history-preservingly with upstream Omalauncher 0.10.0 `5350524` to current PR head [`6894e69`](https://github.com/kvnloo/omalauncher/commit/6894e693dba9598caf1be19d115bb415235ea4de), preserving its exact nine-file recovery diff. GitHub reports **mergeable=true**. [Origin CI #277](https://github.com/SimonSchubert/omalauncher/actions/runs/39439401520) **PASS**, including app/OWL tests, OWL JAR, debug assembly and lint. | Maintainer review/merge. Four earlier physical Galaxy S25 Ultra instrumentation scenarios were on the **prior head**, not rerun against this merge. No production APK installed. A non-essential maintainer update comment attempt received connector HTTP 403; do not claim it posted. |
| [Upstream #130 — terminal tiles stay stopped after exit](https://github.com/SimonSchubert/omalauncher/pull/130) | Original fork head `22249bc` merged history-preservingly with upstream `5350524` to [`6279a25`](https://github.com/kvnloo/omalauncher/commit/6279a25829e616f4fa6b01767783eb3a6f7c813d). Exactly **two** intended files differ from upstream. GitHub reports **mergeable=true**. [Origin CI #278](https://github.com/SimonSchubert/omalauncher/actions/runs/39440054992): **PASS** — JVM Test, Android debug build and lint all completed successfully. | Maintainer review/merge. Prior isolated terminal lifecycle smoke is not physical Android paging proof. No APK installed. |
| [Issue #178 — optional drawer keyboard](https://github.com/SimonSchubert/omalauncher/issues/178) | Fork branch [`fix/drawer-keyboard-focus-178`](https://github.com/kvnloo/omalauncher/tree/fix/drawer-keyboard-focus-178), head `d3d2f030909a97774b907e0f9a1321d62bb18ff4`, based on upstream `5350524`. Adds persisted on-open/on-tap focus preference, isolates the focus policy and four JUnit regression scenarios (source added, **not run**). Exactly three source/test files changed. | Authorized upstream PR creation and exact-head CI/Android acceptance. The upstream create-PR attempt received GitHub connector HTTP 403; no origin PR or passing test suite claimed. |

This wave does not duplicate [Hermes origin #85](https://github.com/SimonSchubert/omalauncher/pull/85), already authored by DeseretSaint and separately integrated for fork QA. No other upstream PR or extra promotion queue created.

### Origin publishing gate — permission failure (October 20)

- **Promotion candidate:** [fork QA #9](https://github.com/kvnloo/omalauncher/pull/9), issue [SimonSchubert/omalauncher#178](https://github.com/SimonSchubert/omalauncher/issues/178), exact head `34abc6bbb67755942579ba68f29cce51841eae5b`.
- **Checks:** [hosted CI PASS](https://github.com/kvnloo/omalauncher/actions/runs/39445522065), Android assembly and lint, and current upstream-main compare: **5 commits ahead / 0 behind, 5 intended files**. No duplicate upstream PR found in search. Default behavior unchanged; physical keyboard/IME acceptance still unknown.
- **Write failure:** Attempted to create *one* origin PR using the connected GitHub integration; GitHub returned HTTP **403 `Resource not accessible by integration`**. The origin PR **was not created**. No second attempt, reviewer ping, or upstream issue comment should be made until a write-capable user-scoped credential or authorized computer session is available. No authenticated `gh` CLI is present in this execution environment.
- **Ready-to-publish head:** `kvnloo:fix/optional-drawer-keyboard-178` → `SimonSchubert/omalauncher:main`; title **Let users open the app drawer without automatically showing the keyboard**. Include `Closes #178`, retain haxx0071's original report and ronyalves083's analysis and honest no-device-test caveat. Use the full tested source head, not a new or duplicate implementation.
- **After publication:** wait for origin workflow authorization, adjust to maintainer feedback, and run signed/isolated-device acceptance without replacing the current installed launcher.
- **DNS #12:** [exact-head hosted CI PASS](https://github.com/kvnloo/omalauncher/actions/runs/39440269801) on `4c7a4912e3e5793e9761a1b9f0e259102e3c88ce`. **Only** VPN, Tailscale MagicDNS and physical-device validation remain as major runtime gates; treat public DNS over Android Private DNS as an unsupported parity case.
- **Owned upstream review scan:** #38, #40, #128, #130, #158 had no recorded maintainer reviews or inline review threads during this check. Avoid sending duplicate progress-only comments.

## 2026-10-20: Native Tern/IME PR sync to upstream 0.10.0

These are **existing upstream PRs**, updated history-preservingly from current upstream main `53505248b9f8616fe308c4972cbbf2f6693a6946`. They were neither merged upstream nor converted out of draft. The separate physical-device gates still apply.

| Original PR | New source head, status, evidence | Gate |
| --- | --- | --- |
| [#128 Tern gestures](https://github.com/SimonSchubert/omalauncher/pull/128) | `ddd28f699dca233e38993eb5a16448071c2c6790` (9 files); **mergeable**, review-ready. The new exact-head [upstream CI #282](https://github.com/SimonSchubert/omalauncher/actions/runs/39443871610) **PASSED**. Original gesture implementation and tests remain unchanged; README and architecture notes include both upstream additions and this PR's details. | Maintainer review and real Android touch-routing acceptance on a signed-compatible isolated install. Do not claim phone tested on this commit. |
| [#38 IME/dictation](https://github.com/SimonSchubert/omalauncher/pull/38) | `7fb37bd7216acf5fc132969c333471fbd0e0cf90` (2 files); **mergeable, draft**. Exact-head [upstream CI #283](https://github.com/SimonSchubert/omalauncher/actions/runs/39444152219) **PASSED**. Retains the prior `KeyboardSink` implementation and all newer upstream release notes. | Physical Gboard dictation, composition, focus recovery, and alternate IME verification. No installed APK update. |
| [#40 Wayland multi-touch](https://github.com/SimonSchubert/omalauncher/pull/40) | `33c059bffa21200bf2640d5b7bfb57e295957588` (10 files); **mergeable, draft**. Exact-head [upstream CI #284](https://github.com/SimonSchubert/omalauncher/actions/runs/39444559624) **PASSED** at exact current head. Both this draft's multi-contact implementation and its dependency on #128's Tern gestures remain in the source. | CI passed; physical multi-touch/cancellation acceptance and maintainer review. If #128 lands via squash/rebase, reconcile #40 against the accepted code instead of presenting duplicated diffs. No installed APK update. |

**Avoid duplicate issue #178 work:** another lane already prepared [fork QA #9](https://github.com/kvnloo/omalauncher/pull/9) and [exact-head passing CI](https://github.com/kvnloo/omalauncher/actions/runs/39445522065). That is the existing tested drawer-keyboard publication candidate; do **not** submit a competing origin PR or repeat the write request that GitHub rejected with 403. An older local branch `fix/drawer-keyboard-focus-178` is not the chosen publication source.

No new upstream PR, duplicate issue, reviewer ping, author credit change, or upstream merge was made in this wave.

## Lossless legacy routing and credit

The full original bodies, comments, attribution, evidence and past claims remain unchanged at these linked archival sources; no legacy thread is deleted or silently rewritten:

- [Prior promotion queue 173 and all comments](https://github.com/kvnloo/oss-factory/issues/173), including [bounded maintenance claim](https://github.com/kvnloo/oss-factory/issues/173#issuecomment-6219456284) and [completed maintenance result](https://github.com/kvnloo/oss-factory/issues/173#issuecomment-6220994173).
- [Original workstream 110 and all comments](https://github.com/kvnloo/oss-factory/issues/110), [Copilot QA 115 and all comments](https://github.com/kvnloo/oss-factory/issues/115), [registration/runbook proposal 111](https://github.com/kvnloo/oss-factory/pull/111).
- Peer-review provenance remains [QA 117](https://github.com/kvnloo/oss-factory/issues/117) for [origin 66](https://github.com/SimonSchubert/omalauncher/pull/66) and [QA 118](https://github.com/kvnloo/oss-factory/issues/118) for [origin 58](https://github.com/SimonSchubert/omalauncher/pull/58); no peer candidate is duplicated or reclassified here.

Legacy references to Linear or factory-local routing are historical, not current authority. Explicit human instructions and the current factory control/HITL policy govern new actions. The parent coordinator owns the registry/directory redirect; continuing project detail belongs here. No broad project queue-clean or merge-readiness claim is made.


## Mandatory preflight: prevent parallel duplicate work

Before creating **any** Omalauncher implementation branch, fork QA PR or upstream PR:

1. Read this queue and its linked origin carriers **first**. Search upstream issues **and all PRs by issue number**, not just a keyword/title search. Inspect the candidate's actual file diff, author and recent checks.
2. Check currently active fork branches/QA PRs and ongoing workers. Prefer improving the original upstream carrier with consent and review over parallel overlapping implementations. Do not create an upstream competitor while the origin PR is open.
3. Work from a specific current upstream SHA on an isolated branch. Verify the PR changes only intended files; fork `main` may be stale. Re-read the blob SHA before every write to avoid overwriting concurrent workers.
4. Report CI only for the **exact head SHA**. Separate JVM/build/lint evidence from APK and physical phone acceptance.
5. If a duplicate is discovered, close its QA carrier **unmerged** with a link to the original, preserve attribution and avoid further promotion. Example: [closed fork QA #11](https://github.com/kvnloo/omalauncher/pull/11) overlaps the prior, fully tested [upstream #158](https://github.com/SimonSchubert/omalauncher/pull/158), which remains the canonical implementation for [#157](https://github.com/SimonSchubert/omalauncher/issues/157).

This gate applies to future waves even when earlier results have disappeared from a GitHub keyword search.


<!-- verified-promotion-wave:2026-10-20:factory111 -->
## Verified promotion results — October 20, 2026

- **Internal factory registration landed:** [oss-factory #188](https://github.com/kvnloo/oss-factory/pull/188) merged as `e1f89aabc`, adding Noctalia/OpenDots; [#111](https://github.com/kvnloo/oss-factory/pull/111) reconciled without discarding those routes (49 existing entries unchanged, 50 total) and merged as `842af1a26`. Current factory `main` routes OmaLauncher to this owning-fork queue. These are **factory** merges, not OmaLauncher upstream merges.
- **Best current origin merge-review candidates:** [#158 live Linux-window recovery](https://github.com/SimonSchubert/omalauncher/pull/158), head `6894e69`, exact-head [CI success](https://github.com/SimonSchubert/omalauncher/actions/runs/39439401520), non-draft/mergeable, no submitted reviews; earlier four isolated S25 Ultra instrumentation scenarios passed before its latest merge-from-main. [#130 terminal tile lifetime](https://github.com/SimonSchubert/omalauncher/pull/130), head `6279a25`, exact-head [CI success](https://github.com/SimonSchubert/omalauncher/actions/runs/39440054992), non-draft/mergeable, no reviews; physical Android paging remains unverified. **Neither origin PR is merged or maintainer-approved.**
- **Other existing origin carriers:** [#128 Tern gestures](https://github.com/SimonSchubert/omalauncher/pull/128) exact-head [CI success](https://github.com/SimonSchubert/omalauncher/actions/runs/39443871610), non-draft, still lacks updated-APK physical touch routing; [#40 multi-touch](https://github.com/SimonSchubert/omalauncher/pull/40) exact-head [CI success](https://github.com/SimonSchubert/omalauncher/actions/runs/39444559624), remains draft for signed-device contact/cancellation; [#38 dictation](https://github.com/SimonSchubert/omalauncher/pull/38) exact-head [CI success](https://github.com/SimonSchubert/omalauncher/actions/runs/39444152219), remains draft for real keyboard/IME acceptance.
- **Fork QA holds:** [#10 drawer opacity](https://github.com/kvnloo/omalauncher/pull/10) `063b4ec`, exact-head [CI success](https://github.com/kvnloo/omalauncher/actions/runs/39444389290), **not yet phone-rendered**; [#9 drawer keyboard](https://github.com/kvnloo/omalauncher/pull/9) `34abc6b`, [CI success](https://github.com/kvnloo/omalauncher/actions/runs/39445522065), not yet device/IME verified; [#12 active-network DNS](https://github.com/kvnloo/omalauncher/pull/12) `4c7a491`, [CI success](https://github.com/kvnloo/omalauncher/actions/runs/39440269801), not yet phone/VPN/MagicDNS validated.
- **Hermes:** [DeseretSaint's original upstream #85](https://github.com/SimonSchubert/omalauncher/pull/85) stays the integration carrier; downstream [#8](https://github.com/kvnloo/omalauncher/pull/8) `dd829d1` has [CI success](https://github.com/kvnloo/omalauncher/actions/runs/39438039870) for installer failure propagation, preserved uv.toml, and first-run UI, but third-party PRoot provenance and real installation remain pending. No competing origin PR.
- **Origin permission blocker:** a scoped attempt to request SimonSchubert's review on #158 returned GitHub connector HTTP 403 `Resource not accessible by integration` on 2026-10-20. No request was created; do not claim reviewer notification, retry via unauthorized routes, or post duplicate origin PRs. Origin merges remain maintainer-controlled.

This is a snapshot tied to exact heads. Recheck heads, checks, changed files, original reporters and maintainer feedback before another promotion attempt; physical proof is never inferred from CI.

<!-- verified-promotion-gate:2026-10-22:omalauncher130 -->
## Material upstream gate refresh — October 22, 2026

- **[#130 terminal tiles](https://github.com/SimonSchubert/omalauncher/pull/130): newly reconciled current-main integration.** Exact original owner head `3e7d0e33e49d013aa11b9a100639b6e189471ea1`; a live `main...kvnloo:fix/terminal-tile-autostart` comparison is **3 ahead / 0 behind**, only two scoped implementation/docs files. Origin PR is OPEN, non-draft and mergeable. Previous recorded `6279a25` is obsolete for current-head checks. New [origin CI run 39779493526](https://github.com/SimonSchubert/omalauncher/actions/runs/39779493526) **completed SUCCESS** on this same SHA, clearing its former in-progress check. No submitted reviews/inline threads found. Existing JVM production-class lifecycle smoke, Gradle app/OWL tests and hosted CI preserve the “no unwanted relaunch after exit” invariant, but updated Galaxy Android paging and live window/device behavior **NOT RUN** in this audit. **Disposition: ORIGIN_LIVE / REVIEW; maintainer decision remains.** No new PR or ping.
- **[#158 Wayland window recovery](https://github.com/SimonSchubert/omalauncher/pull/158): refresh supersedes former 'mergeable' claim.** Original user-owned head `6894e693dba9598caf1be19d115bb415235ea4de` is unchanged, previous [exact-head CI 39439401520](https://github.com/SimonSchubert/omalauncher/actions/runs/39439401520) SUCCESS, but live upstream reports **mergeable=false**; current main is **68 commits ahead** of its last common base (candidate 3 ahead, 9 changed files). **Disposition: ORIGIN_LIVE / CURRENT-MAIN RECONCILIATION REQUIRED**, not review-ready just because old head CI passed. Prior physical Android checks belong to earlier versions and must not be promoted as current-head proof.
- **Provenance/permissions:** preserve the original reports, owner commits and previous testing receipts; no default-branch merge, force update, source commit, new origin issue/PR/comment/reviewer request, CI rerun or device install occurred in this queue refresh. An earlier origin reviewer-request integration 403 is not retried. User authorizes upstream-promotion triage, not maintainer acceptance. This entry is source/CI readback, not native device execution.
