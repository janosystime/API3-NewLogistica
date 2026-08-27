const inquirer = require("inquirer");
const { execSync } = require("child_process");

module.exports = {
  prompter: function (cz, commit) {
    inquirer
      .prompt([
        {
          type: "list",
          name: "type",
          message: "Selecione o tipo de alteração:",
          choices: [
            "feat",
            "fix",
            "docs",
            "refactor",
            "test",
            "chore",
            "style",
            "perf",
            "build",
            "ci",
          ],
        },
        {
          type: "input",
          name: "description",
          message: "Descrição:",
          validate: function (value) {
            if (!value.trim()) {
              return "A descrição não pode ficar vazia.";
            }

            return true;
          },
        },
      ])
      .then((answers) => {
        const message = `${answers.type}: ${answers.description.trim()}`;

        commit(message);
      });
  },
};