import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';
import { parse, stringify } from 'comment-json';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const javaAgentDir = path.join(__dirname, '../javaagent-libs');
const files = fs.readdirSync(javaAgentDir);
const mockitoJar = files.find(
  (file) => file.startsWith('mockito-core') && file.endsWith('.jar')
);

if (mockitoJar) {
  const settingsPath = path.join(__dirname, '../.vscode/settings.json');
  const settings = parse(fs.readFileSync(settingsPath, 'utf8'));

  let existingVmArgs = settings['java.test.config']?.vmArgs || [];
  const javaAgentIndex = existingVmArgs.findIndex((arg) =>
    arg.startsWith(`-javaagent:\${workspaceFolder}/javaagent-libs/mockito-core`)
  );

  if (javaAgentIndex >= 0) {
    existingVmArgs[javaAgentIndex] =
      `-javaagent:\${workspaceFolder}/javaagent-libs/${mockitoJar}`;
    console.log(
      `Updated existing -javaagent: argument at index ${javaAgentIndex}`
    );
  } else {
    existingVmArgs = [
      ...existingVmArgs,
      `-javaagent:\${workspaceFolder}/javaagent-libs/${mockitoJar}`,
    ];
    console.log('Added new -javaagent: argument');
  }

  settings['java.test.config'] = {
    ...settings['java.test.config'],
    vmArgs: existingVmArgs,
  };

  fs.writeFileSync(settingsPath, stringify(settings, null, 2));
  console.log('settings.json updated successfully');
} else {
  console.error('Mockito JAR not found');
}
