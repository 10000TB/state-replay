
## Dev ToDo list

- [ ] The `replay` will be first implemented in a simply way (For each `statepoint`, save variables name and values before, replay will reconstruct a temp workflow execution context, and workflow pipeline will start from there with those finished variables and values from last execution).
- [ ] Under the hoods, steps in workflow pipeline are parsed and run one by one in single thread(multiple threads for `parallel` steps), figure out what is the API end point for accessing raw pipeline steps.
- [X] Figure how workflow pipeline works, and what is the direction to go towards in investigation.
- [X] Implemented basic storage: under `<JOB_HOME>`, similar to `builds`, this plugin now have a root directory for each job called `states`. and a `<BUILD_NUMBER>` directory for each build. Then the checkpoints for each build will be captured and stored in `<JENKINS_HOME>/jobs/<JOB_NAME>/states/<BUILD_NUMBER>`. That being said, checkpoints will be saved in a very similar like builds, and will in most extend copy same features from builds(auto delete older builds, only save most recent builds, etc).
- [x] Step (`statepoint`), which can be used in Groovy pipeline.
