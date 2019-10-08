how to update git password : https://stackoverflow.com/questions/20195304/how-do-i-update-the-password-for-git 

$\begin {aligned}
&\dfrac {\partial}{\partial \boldsymbol v_c}\left [ -\left (\boldsymbol u_o^T\boldsymbol v_c - \log\sum \limits_{w\in Vocab}\exp \left (\boldsymbol u_w^T \boldsymbol v_c \right) \right )\right ] \\

&=\dfrac {\partial}{\partial \boldsymbol v_c}\left ( \log \sum \limits_{w \in Vocab} \exp \left ( \boldsymbol u_w^T \boldsymbol v_c\right )\right ) - \boldsymbol u_o \\

&=\dfrac {\sum \limits_{x \in Vocab} \exp \left ( \boldsymbol u_x^T \boldsymbol v_c\right )\boldsymbol u_x}{\sum \limits_{w \in Vocab} \exp \left ( \boldsymbol u_w^T \boldsymbol v_c\right ) } - \boldsymbol u_o \\

&=\sum_{x\in Vocab} \dfrac {\exp \left ( \boldsymbol u_x^T \boldsymbol v_c\right )\boldsymbol u_x}{\exp \left ( \boldsymbol u_w^T \boldsymbol v_c\right )} - \boldsymbol u_o \\

&=\sum_{x \in Vocab}P(O=x|C=c)\boldsymbol u_x - \boldsymbol u_o
\end {aligned}$
