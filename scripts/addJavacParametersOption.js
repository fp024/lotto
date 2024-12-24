import fs from 'fs';
import path from 'path';

// .settings 디렉토리가 존재하지 않으면 생성
const settingsDir = path.join(process.cwd(), '.settings');
if (!fs.existsSync(settingsDir)) {
  fs.mkdirSync(settingsDir);
}

// org.eclipse.jdt.core.prefs 파일 경로
const prefsFilePath = path.join(settingsDir, 'org.eclipse.jdt.core.prefs');

// 파일 내용
const prefsContent =
  'org.eclipse.jdt.core.compiler.codegen.methodParameters=generate';

// 파일이 존재하는지 확인하고, 내용에 설정이 있는지 확인
let fileContent = '';
if (fs.existsSync(prefsFilePath)) {
  fileContent = fs.readFileSync(prefsFilePath, 'utf8');
}

// 설정이 이미 존재하지 않는 경우에만 추가
if (!fileContent.includes(prefsContent)) {
  fs.appendFileSync(prefsFilePath, `${prefsContent}\n`, 'utf8');
  console.log(
    'The -parameters compiler option has been added for VSCode Java environment.'
  );
} else {
  console.log(
    'The -parameters compiler option already exists for VSCode Java environment.'
  );
}
