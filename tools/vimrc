" ###################################################################
" vimrc 配置
" Edit By Peter
" ###################################################################

" 基本设置
"--------------------------------------------------------------------
set nocompatible		" 关闭 vi 兼容模式 
syntax on				" 自动语法高亮
"set cursorline			" 突出显示当前行 
set ruler				" 打开状态栏标尺 
set shiftwidth=4		" 设定 << 和 >> 命令移动时的宽度为 4 
set softtabstop=4		" 使得按退格键时可以一次删掉 4 个空格 
set tabstop=4			" 设定 tab 长度为 4 
set nobackup			" 覆盖文件时不备份
set incsearch			" 输入搜索内容时就显示搜索结果 
set hlsearch			" 搜索时高亮显示被找到的文本 
set noerrorbells		" 关闭错误信息响铃 
set smartindent			" 开启新行时使用智能自动缩进 
set laststatus=2		" 显示状态栏 (默认值为 1, 无法显示状态栏) 
filetype on				" 打开文件类型检测

" 记住上次打开的位置
au BufReadPost * if line("'\"") > 0|if line("'\"") <= line("$")|exe("norm '\"")|else|exe "norm $"|endif|endif

" 设置在状态行显示的信息 
set statusline=\ %<%F[%1*%M%*%n%R%H]%=\ %y\ %0(%{&fileformat}\ %{&encoding}\ %c:%l/%L%)\ 

hi Comment ctermfg=3


" 阅读代码设置
" ctags
"--------------------------------------------------------------------
set tags=tags;
set autochdir


" taglist
"--------------------------------------------------------------------
let TList_Show_One_File=1				"只显示当前文件的Tag
let TList_Exit_OnlyWindow=1				"当只剩下taglist窗口时，退出vim
let TList_Use_Right_Window=1			"显示在右侧
let TList_GainFocus_On_ToggleOpen=0		"打开时焦点不放在tl窗口中


" cscope
"--------------------------------------------------------------------
if has("cscope")
	set csprg=/usr/bin/cscope
	set csto=1
	set cst
	set nocsverb
	" add any database in current directory
	if filereadable("cscope.out")
		cs add cscope.out
	endif
	set csverb
endif

nmap <C-@>s :cs find s <C-R>=expand("<cword>")<CR><CR>
nmap <C-@>g :cs find g <C-R>=expand("<cword>")<CR><CR>
nmap <C-@>c :cs find c <C-R>=expand("<cword>")<CR><CR>
nmap <C-@>t :cs find t <C-R>=expand("<cword>")<CR><CR>
nmap <C-@>e :cs find e <C-R>=expand("<cword>")<CR><CR>
nmap <C-@>f :cs find f <C-R>=expand("<cfile>")<CR><CR>
nmap <C-@>i :cs find i ^<C-R>=expand("<cfile>")<CR>$<CR>
nmap <C-@>d :cs find d <C-R>=expand("<cword>")<CR><CR>



