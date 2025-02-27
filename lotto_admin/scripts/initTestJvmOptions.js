import { readdir, readFile, writeFile } from 'fs/promises';
import path from 'path';
import { fileURLToPath } from 'url';
import { parse, stringify } from 'comment-json';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const javaAgentDir = path.join(__dirname, '../javaagent-libs');
const files = await readdir(javaAgentDir);
const mockitoJar = files.find(
  (file) => file.startsWith('mockito-core') && file.endsWith('.jar')
);

if (mockitoJar) {
  const settingsPath = path.join(__dirname, '../.vscode/settings.json');
  const settings = parse(await readFile(settingsPath, 'utf8'));

  let existingVmArgs = settings['java.test.config']?.vmArgs || [];
  const mockitoJavaAgentIndex = existingVmArgs.findIndex((arg) =>
    arg.startsWith(`-javaagent:\${workspaceFolder}/javaagent-libs/mockito-core`)
  );

  const mockitoJavaAgentOption = `-javaagent:\${workspaceFolder}/javaagent-libs/${mockitoJar}`;

  if (mockitoJavaAgentIndex >= 0) {
    if (existingVmArgs[mockitoJavaAgentIndex] !== mockitoJavaAgentOption) {
      console.log(
        `Updated existing ${existingVmArgs[mockitoJavaAgentIndex]} to ${mockitoJavaAgentOption}`
      );
      existingVmArgs[mockitoJavaAgentIndex] = mockitoJavaAgentOption;
    }
  } else {
    existingVmArgs = [...existingVmArgs, mockitoJavaAgentOption];
    console.log(`Added new ${mockitoJavaAgentOption}`);
  }

  const shareOptionIndex = existingVmArgs.findIndex((arg) =>
    arg.startsWith(`-Xshare:`)
  );

  const shareOffOption = '-Xshare:off';

  if (shareOptionIndex >= 0) {
    if (existingVmArgs[shareOptionIndex] !== shareOffOption) {
      console.log(
        `Updated existing ${existingVmArgs[shareOptionIndex]} to ${shareOffOption}`
      );
      existingVmArgs[shareOptionIndex] = shareOffOption;
    }
  } else {
    existingVmArgs = [...existingVmArgs, shareOffOption];
    console.log(`Added new ${shareOffOption}`);
  }

  settings['java.test.config'] = {
    ...settings['java.test.config'],
    vmArgs: existingVmArgs,
  };

  await writeFile(settingsPath, stringify(settings, null, 2, { eol: '\n' }));
  console.log('settings.json updated successfully');
} else {
  console.error('Mockito JAR not found');
}
