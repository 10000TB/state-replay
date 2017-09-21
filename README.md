# state-replay
Yet another Jenkins plugin. This plugin helps you reserve the state before the first failing point, and let you replay the pipeline after you fix the failure. Isn't this great?

## Motivation 
>Skip this section and directly go for the juice.

When I was working on a long-running pipeline(it usually takes 6-8 hours to finish the pipeline) for my organization, it was extremely hard to replay the pipeline in a cost efficiently way. For example, when the pipeline failed in the middle(say it failed after 3 hours of running), I then need to restart the pipeline from scratch and wait for 3 hours to reach last failure point after I resolve issue(s) in either pipeline or product code base. Imagine that the pipeline could fail anywhere in the middle, and you only 24 hours a day, then it becomes extremely hard to productionize the pipeline in a efficient way and in a short time period. At the same time, I attended 2017 Jenkins World Conference and was amazed by how much access you can have to Jenkins system through plugin, then I had the idea of writting plugin to solve the problem.
At the same time, my organization had a `Cloudbees Jenkins Team` subscription with Cloudbees Inc. I happened to know that Cloudbees already has a similar plugin to solve the exact problem, Horray, problem solved!!!

However, they close-sourced it and make it only available on `Jenkins Enterprise` version and my organization doesn't have that. Therefore, there is still no such plugin open to the community, which strenghened desire to proceed to work on this plugin. 

Then, here is the plugin. 

## Install

## Contribute

## Liscense
