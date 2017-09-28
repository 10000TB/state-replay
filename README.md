# state-replay
One great workflow pipeline to rule them all. This Jenkins plugin(<strong>WIP</strong>) will help you save the runtime state in workflow pipeline, and let you replay the pipeline from predefined checkpoint. For example, you can replay pipeline from the last failing point after you fix infra or product related failure(s). No need to waste hour(s) in replaying from beginning.

## Current Status
What has been done:
1. implemented the step (`statepoint`), which can be used in Groovy pipeline.

Whats next to finish the plugin:
1. Implement replaying the checkpoint(s) before the last failure point(s).

What is actually going on:
1. David Hu(@10000TB) is working on figuring out how to implement the "runtime state saving and replay"

## Road Map
1. Beta version 1.0: `statepoint` can be used to define checkpoint in Groovy pipeline. Such pipelines can replay from a checkpoint that is before the last failure point(s). (Note: If such pipelines have parallel steps, replay can happen from each corresponding checkpoint right before their last failure.)
2. Version 2.0: Bug fixes to make 1.0 reliable.
3. Complete version 3.0: replay can happen from predefined checkpoint.

## Motivation
>For open sourced Jenkins distribution.
TL;DR
1. Current workflow pipeline doesn't support replaying from checkpoint.
2. Heavy product often needs long-running pipeline on CI side, and replay from beginning everytime wastes time in repeating steps that already passed.
3. Introduce such checkpoint plugin, help debugging/development effiency of developing workflow pipeline in groovy.

The actual story or my testimonials of there should be such plugin:
When I was working on a long-running pipeline(it usually takes 6-8 hours to finish the pipeline) for my organization, it was extremely hard to replay the pipeline in a cost efficiently way if it fails after running for a few hours. For example, when the pipeline failed in the middle(say it failed after 3 hours of running), I then need to restart the pipeline from scratch and wait for 3 hours to reach last failure point after I resolve issue(s) (it could infra, pipeline syntax, or product code base). Imagine that the pipeline could fail anywhere in the middle, and you only 24 hours a day, then it becomes extremely hard to productionize the pipeline in a efficient way and in a short time period. At the same time, I attended 2017 Jenkins World Conference and was amazed by how much access you can have to Jenkins system through plugin, then I had the idea of writting plugin to solve the problem.

## Install
(TODO: David Hu ) add installation instructions.

## Contribute
(TODO: David Hu) add contribution guide.

## Liscense
